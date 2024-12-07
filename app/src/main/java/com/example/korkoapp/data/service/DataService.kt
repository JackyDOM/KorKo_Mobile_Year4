package com.example.korkoapp.data.service

import com.example.korkoapp.data.api.ApiResponse
import com.example.korkoapp.data.model.Data
import com.example.korkoapp.data.model.DataBanner
import com.example.korkoapp.data.model.DataCafe
import com.example.korkoapp.data.model.DataSea
import com.example.korkoapp.data.model.DataTemple
import retrofit2.http.GET

interface DataService {
    @GET("/api/banner")
    suspend fun loadDataBanner(): ApiResponse<List<DataBanner>>

    @GET("/api/foods")
    suspend fun loadDataDish(): ApiResponse<List<Data>>

    @GET("/api/sea")
    suspend fun loadDataSea(): ApiResponse<List<DataSea>>

    @GET("/api/temple")
    suspend fun loadDataTemple(): ApiResponse<List<DataTemple>>

    @GET("/api/cafe")
    suspend fun loadDataCafe(): ApiResponse<List<DataCafe>>
}