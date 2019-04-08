package com.willowtreeapps.common.repo

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
import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl
import kotlinx.serialization.json.Json

open class KtorProfilesRepository : ProfilesRepository {
    private val baseUrl = "https://willowtreeapps.com"

    override suspend fun profiles(): GatewayResponse<List<Profile>, GenericError> {
        return try {
            val response: ProfileListHolder = client.get {
                apiUrl("api/v1.0/profiles")
            }
            GatewayResponse.createSuccess(response.profiles, 200, "Success")
        } catch (e: Exception) {
            GatewayResponse.createError(GenericError(e.message
                    ?: "Failure"), 200, e.message ?: "failure")
        }
    }

    private val client by lazy {
        return@lazy try {

            HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(Json.nonstrict).apply {
                        setMapper(ProfileListHolder::class, ProfileListHolderSerializer())
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
        header(HttpHeaders.CacheControl, "no-cache")
        url {
            takeFrom(baseUrl)
            encodedPath = path
        }
    }
}


class ProfileListHolder(val profiles: List<Profile>)

class ProfileListHolderSerializer : KSerializer<ProfileListHolder> {

    override val descriptor = object : SerialClassDescImpl("Inner") {}

    override fun deserialize(input: Decoder): ProfileListHolder {
        val list = input.decodeSerializableValue(Profile.serializer().list)
        return ProfileListHolder(list)
    }

    override fun serialize(encoder: Encoder, obj: ProfileListHolder) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}