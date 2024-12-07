package com.example.korkoapp.data.api

class ApiResponse<T> (
    val status: Int,
    val message: String,
    val data: T?
)