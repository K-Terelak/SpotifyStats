package kt.mobile.spotify_stats.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kt.mobile.spotify_stats.feature_artist.data.remote.ArtistApi
import kt.mobile.spotify_stats.feature_artist.data.repository.ArtistRepositoryImpl
import kt.mobile.spotify_stats.feature_artist.domain.repository.ArtistRepository
import kt.mobile.spotify_stats.feature_artist.domain.use_case.ArtistUseCases
import kt.mobile.spotify_stats.feature_artist.domain.use_case.GetArtistUseCase
import kt.mobile.spotify_stats.feature_artist.domain.use_case.GetArtistsTopTracksUseCase
import kt.mobile.spotify_stats.feature_artist.domain.use_case.GetRelatedArtistsUseCase
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArtistModule {


    @Singleton
    @Provides
    fun provideArtistApi(@Named("retrofitToken") retrofit: Retrofit): ArtistApi =
        retrofit.create(ArtistApi::class.java)


    @Provides
    @Singleton
    fun provideArtistRepository(
        api: ArtistApi,
        sharedPreferences: SharedPreferences
    ): ArtistRepository {
        return ArtistRepositoryImpl(api, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideArtistUseCases(repository: ArtistRepository): ArtistUseCases {
        return ArtistUseCases(
            getArtist = GetArtistUseCase(repository),
            getArtistsTopTracks = GetArtistsTopTracksUseCase(repository),
            getRelatedArtists = GetRelatedArtistsUseCase(repository)
        )
    }
}