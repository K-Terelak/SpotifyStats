package kt.mobile.spotify_stats.core.data

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val error: Int? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(error: Int, data: T? = null) : Resource<T>(data, error)
}
