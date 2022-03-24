package kt.mobile.spotify_stats.core.util

import java.time.Duration
import java.time.Instant

object DateFormatUtil {

    fun calculateTimeDIff(formattedString: String): String {
        val date = Instant.parse(formattedString)
        val currentTimestamp = Instant.now()
        val diff = Duration.between(date, currentTimestamp)
        return when {
            diff.toMinutes() in 0..59 -> {
                diff.toMinutes().toString() + "m"
            }
            diff.toHours() in 1..23 -> {
                diff.toHours().toString() + "h"
            }
            diff.toDays() in 1..14 -> {
                diff.toDays().toString() + "d"
            }
            else -> {
                (diff.toDays() / 7).toInt().toString() + "w"
            }
        }
    }
}