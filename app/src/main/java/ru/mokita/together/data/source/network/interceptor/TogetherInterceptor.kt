package ru.mokita.together.data.source.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.mokita.together.data.source.local.SharedPreferencesHelper
import javax.inject.Inject

class TogetherInterceptor @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferencesHelper.token()
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}