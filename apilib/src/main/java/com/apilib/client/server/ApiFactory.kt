package com.apilib.client.server

import com.apilib.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiFactory {
    fun standardClient(): CallAPI {

        val interceptors = ArrayList<Interceptor>(1)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptors.add(loggingInterceptor)

        return createClient(interceptors, BuildConfig.SERVER_URL, 10)
    }

    fun createClient(interceptors: List<Interceptor>, baseUrl: String, timeoutSeconds: Long): CallAPI {
        val okClientBuilder = OkHttpClient.Builder()

        interceptors.forEach {
            okClientBuilder.addInterceptor(it)
        }

        val okClient = okClientBuilder
            .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
            .writeTimeout(timeoutSeconds, TimeUnit.SECONDS)
            .readTimeout(timeoutSeconds, TimeUnit.SECONDS)
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okClient)
            .build()

        return CallAPI(retrofit)
    }
}