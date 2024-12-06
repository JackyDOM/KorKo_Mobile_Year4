package com.example.korkoapp.service

import com.example.korkoapp.api.ApiResponse
import com.example.korkoapp.model.Data
import com.example.korkoapp.model.DataBanner
import retrofit2.http.GET

interface DataService {
    @GET("/api/banner")
    suspend fun loadDataBanner(): ApiResponse<List<DataBanner>>

    @GET("/api/foods")
    suspend fun loadDataDish(): ApiResponse<List<Data>>
}