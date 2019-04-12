package com.willowtreeapps.common.repo

import com.willowtreeapps.common.util.profile
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.takeFrom
import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl
import kotlinx.serialization.json.Json
import kotlin.coroutines.CoroutineContext

open class KtorCatsRepository(private val networkContext: CoroutineContext) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = networkContext + Job()

    companion object {
        private const val baseUrl = "https://api.thecatapi.com"
        private const val ALL_BREEDS_PATH = "v1/breeds"
        private fun breedImagePath(breed: String) = "v1/images/search?breed_id=$breed"
        private const val CAT_API_KEY = "f3694dab-65c5-4137-bd4d-a0d2477a6309"
    }


    suspend fun allBreeds(): GatewayResponse<List<Cat>, GenericError> {
        return try {
            val response: CatBreedListHolder = client.get {
                apiUrl(ALL_BREEDS_PATH)
            }

            val listOfBreeds = profile("fetch all ${response.cats.size} cat image data") {
                response.cats.map {
                    async {
                        val image = retrySuccessOrThrow(2, 2000, Exception(), suspend { image(it.id) })
                        Cat(id = it.id,
                                breed = it.name,
                                altNames = it.alt_names?.split(",") ?: listOf(),
                                imageUrl = image.response?.first()?.url!!
                        )
                    }
                }.awaitAll()
            }
            GatewayResponse.createSuccess(listOfBreeds, 200, "Success")
        } catch (e: Exception) {
            GatewayResponse.createError(GenericError(e.message
                    ?: "Failure"), 500, e.message ?: "failure")
        }
    }

    suspend fun image(breedId: String): GatewayResponse<List<CatImageResponse>, GenericError> {
        return try {
            val response: CatImageListHolder = client.get {
                apiUrl(breedImagePath(breedId))
            }
            GatewayResponse.createSuccess(response.images, 200, "Success")
        } catch (e: Exception) {
            GatewayResponse.createError(GenericError(e.message
                    ?: "Failure"), 500, e.message ?: "failure")
        }
    }

    private val client by lazy {
        return@lazy try {

            HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(Json.nonstrict).apply {
                        setMapper(CatBreedListHolder::class, CatBreedListHolderSerializer())
                        setMapper(CatImageListHolder::class, CatImageListHolderSerializer())
                    }
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }


        } catch (e: Exception) {
            throw RuntimeException("Error initialization: ${e.message}")
        }
    }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        header(HttpHeaders.CacheControl, io.ktor.client.utils.CacheControl.MAX_AGE)
        header("x-api-key", CAT_API_KEY)
        url {
            takeFrom(baseUrl)

            encodedPath = path
        }
    }
}

@Serializable
data class CatBreed(val id: String,
                    val name: String,
                    val origin: String,
                    val temperament: String,
                    val description: String,
                    @Optional
                    val vetstreet_url: String = "",
                    @Optional
                    val alt_names: String? = null)


@Serializable
data class CatImageResponse(@Optional val breed: CatBreed? = null,
                            val url: String)


data class CatImageListHolder(val images: List<CatImageResponse>)

data class Cat(val id: String,
               val breed: String,
               val altNames: List<String>,
               val imageUrl: String)


class CatBreedListHolder(val cats: List<CatBreed>)

class CatBreedListHolderSerializer : KSerializer<CatBreedListHolder> {

    override val descriptor = object : SerialClassDescImpl("Inner") {}

    override fun deserialize(input: Decoder): CatBreedListHolder {
        val list = input.decodeSerializableValue(CatBreed.serializer().list)
        return CatBreedListHolder(list)
    }

    override fun serialize(encoder: Encoder, obj: CatBreedListHolder) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class CatImageListHolderSerializer : KSerializer<CatImageListHolder> {

    override val descriptor = object : SerialClassDescImpl("Inner") {}

    override fun deserialize(input: Decoder): CatImageListHolder {
        val list = input.decodeSerializableValue(CatImageResponse.serializer().list)
        return CatImageListHolder(list)
    }

    override fun serialize(encoder: Encoder, obj: CatImageListHolder) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
