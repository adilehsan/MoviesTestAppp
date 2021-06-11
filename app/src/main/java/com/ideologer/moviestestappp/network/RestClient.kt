package com.ideologer.moviestestappp.network

import com.ideologer.moviestestappp.utils.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {
    var client = OkHttpClient()
        .newBuilder().addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }).callTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES).connectTimeout(1, TimeUnit.MINUTES).build()

    @get:Synchronized
    private val retrofitClient: Retrofit
        private get() = Retrofit.Builder()
            .baseUrl(Constant.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    val apiService: ApiServices
        get() = retrofitClient.create(ApiServices::class.java)
}