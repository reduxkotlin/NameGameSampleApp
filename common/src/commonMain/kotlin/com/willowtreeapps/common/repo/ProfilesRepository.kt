package com.willowtreeapps.common.repo

import kotlinx.serialization.Serializable


interface ProfilesRepository {
    suspend fun profiles(): GatewayResponse<List<Profile>, GenericError>
}

@Serializable
data class Profile(
        //consider using ItemId inline class once supported in kotlinx.serialization
        val id: String,
        val type: String,
        val slug: String,
        val jobTitle: String? = null,
        val firstName: String,
        val lastName: String,
        val headshot: Headshot,
        val socialLinks: List<SocialLinks>? = null) {

}

@Serializable
data class Headshot(val type: String,
                    val id: String,
                    val url: String? = null,
                    val height: String? = null,
                    val width: String? = null)

@Serializable
data class SocialLinks(val type: String,
                       val callToAction: String,
                       val url: String? = null)


