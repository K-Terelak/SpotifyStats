package kt.mobile.spotify_stats

import org.junit.Rule

abstract class UnitTest {
    @Suppress("LeakingThis")
    @Rule
    @JvmField
    val injectMocks = InjectMocksRule.create(this@UnitTest)
}