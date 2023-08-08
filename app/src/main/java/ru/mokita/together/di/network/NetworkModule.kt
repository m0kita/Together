package ru.mokita.together.di.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.mokita.together.data.source.local.SharedPreferencesHelper
import ru.mokita.together.data.source.network.api.TogetherApi
import ru.mokita.together.data.source.network.interceptor.TogetherInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideTogetherInterceptor(sharedPreferencesHelper: SharedPreferencesHelper): TogetherInterceptor = TogetherInterceptor(sharedPreferencesHelper)

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        togetherInterceptor: TogetherInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(togetherInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://ktortogether.profsoft.online/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): TogetherApi =
        retrofit.create(TogetherApi::class.java)

}