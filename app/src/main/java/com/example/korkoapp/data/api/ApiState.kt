package com.example.korkoapp.data.api

data class ApiState<T> (
    val state: State,
    val data: T? = null,
    val errorMessage: String? = null
)

enum class State {
    SUCCESS, ERROR
}

