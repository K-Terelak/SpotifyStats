package kt.mobile.spotify_stats.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ClientBearer

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ClientAuth

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class RetrofitToken

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class RetrofitAuth