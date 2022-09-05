package com.alfayedoficial.coreNetwork.core.di

import android.content.Context
import com.alfayedoficial.coreNetwork.api.ApiService
import com.alfayedoficial.kotlinutils.KUPreferences
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.alfayedoficial.coreNetwork.utilities.NetworkConstants
import com.alfayedoficial.coreNetwork.utilities.NetworkConstants.BASE_URL
import com.alfayedoficial.coreNetwork.utilities.NetworkConstants.CONTENT_TYPE
import com.alfayedoficial.coreNetwork.utilities.NetworkConstants.REQUEST_TIME
import com.alfayedoficial.coreNetwork.utilities.NetworkConstants.REQUEST_TIME_Debug
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Provides
    @Singleton
    fun provideHeadersInterceptor() = Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Content-Type", CONTENT_TYPE)
                    .addHeader("Accept", CONTENT_TYPE)
                    .build()
            )
        }


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headersInterceptor: Interceptor,
        logging: HttpLoggingInterceptor,
        chuckerInterceptor : ChuckerInterceptor,
    ): OkHttpClient {
        return if (NetworkConstants.BuildConfig_DEBUG) {
            OkHttpClient.Builder()
                .readTimeout(REQUEST_TIME_Debug, TimeUnit.MINUTES)
                .connectTimeout(REQUEST_TIME_Debug, TimeUnit.MINUTES)
                .addInterceptor(headersInterceptor)
                .addNetworkInterceptor(logging)
                .addInterceptor(chuckerInterceptor)
                .build()
        } else {
            OkHttpClient.Builder()
                .readTimeout(REQUEST_TIME, TimeUnit.MINUTES)
                .connectTimeout(REQUEST_TIME, TimeUnit.MINUTES)
                .addInterceptor(headersInterceptor)
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .serializeNulls() // To allow sending null values
            .create()
    }

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun getDynamicRetrofitClient(
        gson: Gson,
        okHttpClient: OkHttpClient,
    ): Retrofit {

        val dateFormatter = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(dateFormatter))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun getChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .alwaysReadResponseBody(true)
            .build()
    }
}
