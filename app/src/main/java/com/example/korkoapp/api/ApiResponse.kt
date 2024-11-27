package com.example.korkoapp.api

class ApiResponse<T> (
    val status: Int,
    val message: String,
    val data: T?
)