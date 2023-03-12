package com.cheurfi.discography.di

import com.cheurfi.discography.BuildConfig
import com.cheurfi.repository.network.ArtistRepository
import com.cheurfi.repository.network.ArtistService
import com.cheurfi.repository.network.MusicBrainzArtistRepository
import com.cheurfi.repository.network.RecordService
import com.cheurfi.utils.network.RequestInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
abstract class NetworkModule {


    @Binds
    abstract fun bindArtistRepository(musicBrainzArtistRepository: MusicBrainzArtistRepository): ArtistRepository


    companion object {
        private val client: OkHttpClient.Builder
            get() = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
                .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MINUTES)
                .addInterceptor(
                    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                )

        private val retrofit: Retrofit
            get() = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(client.addInterceptor(RequestInterceptor("Discography", "some@email.com")).build())
                .build()

        @Provides
        fun provideArtistService(): ArtistService = retrofit.create(ArtistService::class.java)

        @Provides
        fun provideRecordService(): RecordService = retrofit.create(RecordService::class.java)

        const val CONNECT_TIMEOUT = 1L
        const val WRITE_TIMEOUT = 1L
        const val READ_TIMEOUT = 1L
    }

}
