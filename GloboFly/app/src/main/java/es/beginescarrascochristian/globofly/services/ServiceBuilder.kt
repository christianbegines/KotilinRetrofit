package es.beginescarrascochristian.globofly.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

object ServiceBuilder {

    private const val URL = "http://10.0.2.2:9000/"
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    
    private val okHttp:OkHttpClient.Builder = OkHttpClient.Builder()

    private val builder:Retrofit.Builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit:Retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T{
       return retrofit.create(serviceType)
    }
}