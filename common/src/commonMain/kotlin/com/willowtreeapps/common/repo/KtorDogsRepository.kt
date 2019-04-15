package com.willowtreeapps.common.repo

import com.willowtreeapps.common.util.TimeUtil
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
import io.ktor.http.CacheControl
import io.ktor.http.HttpHeaders
import io.ktor.http.takeFrom
import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlin.coroutines.CoroutineContext

open class KtorDogsRepository(private val networkContext: CoroutineContext) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = networkContext + Job()

    companion object {
        private const val baseUrl = "https://dog.ceo"
        private const val ALL_BREEDS_PATH = "api/breeds/list/all"
        private fun randomBreedImagePath(breed: String) = "api/breed/$breed/images/random"
        private fun randomSubBreedImagePath(breed: String, subBreed: String) = "api/breed/$breed/$subBreed/images/random"
    }


    suspend fun dogs(): GatewayResponse<List<Dog>, GenericError> {
        return try {
            val response: DogResponse = client.get {
                apiUrl(ALL_BREEDS_PATH)
            }
            val listOfBreeds = profile("fetching all ${response.message.keys.size} dog image data") {
                response.message.map { breed ->
                    if (breed.value.isNotEmpty()) {
                        breed.value.map { subBreed ->
                            async {
                                val dogImageResponse =
                                        try {
                                            randomSubBreedImage(breed.key, subBreed)
                                        } catch (e: Exception) {
                                            GatewayResponse.createSuccess<DogImageResponse, GenericError>(DogImageResponse("200", ""), 200, "")
                                        }
                                Dog(breed = breed.key.capitalize(), subBreed = subBreed.capitalize(), imageUrl = dogImageResponse.response?.message!!)
                            }
                        }
                    } else {

                        listOf(
                                async {
                                    val dogImageResponse =
                                            try {
                                                randomBreedImage(breed.key)
                                            } catch (e: Exception) {
                                                com.willowtreeapps.common.Logger.d("Failure fetching image: ${e.message}")
                                                GatewayResponse.createSuccess<DogImageResponse, GenericError>(DogImageResponse("200", ""), 200, "")
                                            }
                                    Dog(breed = breed.key.capitalize(), imageUrl = dogImageResponse.response?.message!!)
                                }
                        )
                    }
                }.flatten()
                        .awaitAll()
            }
            GatewayResponse.createSuccess(listOfBreeds, 200, "Success")
        } catch (e: Exception) {
            com.willowtreeapps.common.Logger.d("Failure fetching dogs: $e")
            com.willowtreeapps.common.Logger.d("Failure fetching dogs: ${e::class.qualifiedName}")
            GatewayResponse.createError(GenericError(e.message
                    ?: "Failure"), 500, e.message ?: "failure")
        }
    }

    suspend fun randomBreedImage(breed: String): GatewayResponse<DogImageResponse, GenericError> {
//        return try {
        val response: DogImageResponse = client.get {
            apiUrl(randomBreedImagePath(breed))
        }
        return GatewayResponse.createSuccess(response, 200, "Success")
//        } catch (e: Exception) {
//            com.willowtreeapps.common.Logger.d("Failure fetching breed image: ${e.message}")
//            GatewayResponse.createError(GenericError(e.message
//                    ?: "Failure"), 500, e.message ?: "failure")
//        }
    }

    suspend fun randomSubBreedImage(breed: String, subBreed: String): GatewayResponse<DogImageResponse, GenericError> {
//        return try {
        val response: DogImageResponse = client.get {
            apiUrl(randomSubBreedImagePath(breed, subBreed))
        }
        return GatewayResponse.createSuccess(response, 200, "Success")
//        } catch (e: Exception) {
//            com.willowtreeapps.common.Logger.d("Failure fetching subbreed image: ${e.message}")
//            GatewayResponse.createError(GenericError(e.message
//                    ?: "Failure"), 500, e.message ?: "failure")
//        }
    }

    private val client by lazy {
        return@lazy try {

            HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(Json.nonstrict).apply {
                        setMapper(DogResponse::class, DogResponse.serializer())
                        setMapper(DogImageResponse::class, DogImageResponse.serializer())
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
        url {
            takeFrom(baseUrl)
            encodedPath = path
        }
    }
}

@Serializable
data class DogResponse(val status: String,
                       val message: Map<String, List<String>>)

@Serializable
data class DogImageResponse(val status: String,
                            val message: String)

data class Dog(val breed: String,
               val subBreed: String? = null,
               val imageUrl: String)

