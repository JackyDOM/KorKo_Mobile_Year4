package com.example.korkoapp.api

import com.example.korkoapp.service.DataService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager private constructor() {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)  // Increased connection timeout
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.43.90:35174")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val dataService: DataService = retrofit.create(DataService::class.java)

    companion object {
        private var instance: ApiManager? = null

        fun getDataService(): DataService {
            if (instance == null) {
                instance = ApiManager()
            }
            return instance!!.dataService
        }
    }

}