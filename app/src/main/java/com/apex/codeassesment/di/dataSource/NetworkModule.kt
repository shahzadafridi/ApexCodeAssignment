package com.apex.codeassesment.di.dataSource

import android.content.Context
import com.apex.codeassesment.BuildConfig
import com.apex.codeassesment.data.remote.NetworkApiCreator
import com.apex.codeassesment.data.remote.RandomUserAPI
import com.apex.codeassesment.data.remote.RemoteDataSource
import com.apex.codeassesment.util.error.HttpExceptionHandler
import com.apex.codeassesment.util.resource.ResourceManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val TIMEOUT_SECONDS = 20L
private const val BASE_URL = "https://randomuser.me"
@Module
object NetworkModule {

    @Provides
    fun provideRemoteDataSource(randomUserAPI: RandomUserAPI, exceptionHandler: HttpExceptionHandler): RemoteDataSource = RemoteDataSource(randomUserAPI = randomUserAPI,exceptionHandler = exceptionHandler)

    @Provides
    fun provideJsonMapper() = Gson()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return builder.build()
    }

    @Provides
    fun httpExceptionHandler(
        resourceManager: ResourceManager
    ): HttpExceptionHandler = HttpExceptionHandler(resourceManager)


    @Provides
    fun provideApiCreator(
        okHttpClient: OkHttpClient
    ): NetworkApiCreator {
        return NetworkApiCreator(okHttpClient, BASE_URL)
    }

    @Provides
    fun provideRandomUserApi(
        networkApiCreator: NetworkApiCreator,
    ) = networkApiCreator.create(RandomUserAPI::class.java)
}