package com.example.korkoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.korkoapp.data.api.ApiManager
import com.example.korkoapp.data.api.ApiState
import com.example.korkoapp.data.api.State
import com.example.korkoapp.data.model.Data
import kotlinx.coroutines.launch

class DishViewModel: ViewModel(){
    private val _dataState = MutableLiveData<ApiState<List<Data>>>()
    val datstate: LiveData<ApiState<List<Data>>> get() = _dataState

    fun loadDataDish(){
        val dataService = ApiManager.getDataService()
        viewModelScope.launch {
            try {
                val dataResponse = dataService.loadDataDish()
                if(dataResponse.status == 200){
                    // Filter the data to include only those with category_name == "dish"
                    val filteredData = dataResponse.data?.filter {
                        it.category.category_name.equals("Dish", ignoreCase = true)
                    }
                    _dataState.postValue(ApiState(State.SUCCESS, filteredData))
                }else{
                    _dataState.postValue(ApiState(State.ERROR, null))
                }
            }catch (ex: Exception){
                Log.e("ruppite", "Error While loading data: $ex")
            }
        }
    }
}