package com.willowtreeapps.common.repo

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable


interface ProfilesRepository {
    suspend fun profiles(): GatewayResponse<List<Profile>, GenericError>
}

@Serializable
data class Profile(val id: String,
                   val type: String,
                   val slug: String,
                   @Optional
                   val jobTitle: String? = null,
                   val firstName: String,
                   val lastName: String,
                   val headshot: Headshot,
                   @Optional
                   val socialLinks: List<SocialLinks>? = null)

@Serializable
data class Headshot(val type: String,
                    val id: String,
                    @Optional
                    val url: String? = null,
                    @Optional
                    val height: String? = null,
                    @Optional
                    val width: String? = null)

@Serializable
data class SocialLinks(val type: String,
                       val callToAction: String,
                       @Optional
                       val url: String? = null)


