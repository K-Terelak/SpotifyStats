package kt.mobile.spotify_stats.core.util

import kt.mobile.spotify_stats.BuildConfig
import java.util.*

fun String.toBearerHeader(): String = "Bearer $this"

fun String.toBasicHeader(): String = "Basic $this"

fun encodeBasicHeader(): String = Base64.getEncoder().encodeToString(("${BuildConfig.CLIENT_ID}:${BuildConfig.CLIENT_SECRET}").toByteArray()).toBasicHeader()
