package di

import com.cheurfi.discography.BuildConfig
import dagger.Module
import dagger.Provides
import com.cheurfi.search.network.MusicBrainzService
import com.cheurfi.utils.network.RequestInterceptor
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {
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

    companion object {
        const val CONNECT_TIMEOUT = 1L
        const val WRITE_TIMEOUT = 1L
        const val READ_TIMEOUT = 1L
    }

    @Provides
    fun provideUserNetworkService(): MusicBrainzService = retrofit.create(MusicBrainzService::class.java)
}
