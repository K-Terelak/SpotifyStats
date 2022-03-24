package kt.mobile.spotify_stats.feature_home.data.remote.response.get_profile


import com.google.gson.annotations.SerializedName
import kt.mobile.spotify_stats.core.data.dto.response.ExternalUrls
import kt.mobile.spotify_stats.core.data.dto.response.Followers
import kt.mobile.spotify_stats.core.data.dto.response.Image
import kt.mobile.spotify_stats.feature_home.domain.models.MyProfile

data class MyProfileResponse(
    @SerializedName("country")
    val country: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("explicit_content")
    val explicitContent: ExplicitContent,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    @SerializedName("followers")
    val followers: Followers,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("product")
    val product: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("uri")
    val uri: String
) {
    fun toMyProfile(): MyProfile {
        return MyProfile(
            country = country,
            displayName = displayName,
            account_url = externalUrls.spotify,
            followersCount = followers.total,
            image = images.firstOrNull()?.url
        )
    }

}