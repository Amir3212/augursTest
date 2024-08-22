package com.inerviewapp.utils.di


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.inerviewapp.api.AppApi
import com.inerviewapp.utils.network.interceptor.DummyJsonAuthInterceptor
import com.inerviewapp.utils.redux.ApplicationState
import com.inerviewapp.utils.redux.Store
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BaseAppModule {
    @Provides
    @Singleton
    fun provideHttpLogger(dummyJsonInterceptor: DummyJsonAuthInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(dummyJsonInterceptor)
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://glideline.augurs.app/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }


    @Provides
    @Singleton
    fun provideStore(): Store<ApplicationState> = Store(ApplicationState())


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): AppApi {
        return retrofit.create(AppApi::class.java)
    }


    @Provides
    @Singleton
    fun provideSharedPrefs(applicationState: Application): SharedPreferences =
        applicationState.getSharedPreferences("com.utils", Context.MODE_PRIVATE)


}
