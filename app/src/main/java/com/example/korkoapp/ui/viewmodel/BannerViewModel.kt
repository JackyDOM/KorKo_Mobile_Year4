package com.example.korkoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.korkoapp.data.api.ApiManager
import com.example.korkoapp.data.api.ApiState
import com.example.korkoapp.data.api.State
import com.example.korkoapp.data.model.DataBanner
import kotlinx.coroutines.launch

// State-Holder
class BannerViewModel: ViewModel() {
    private val _dataState = MutableLiveData<ApiState<List<DataBanner>>>()
    val dataState: LiveData<ApiState<List<DataBanner>>> get() = _dataState

    fun loadData(){
        val dataService = ApiManager.getDataService()
        viewModelScope.launch {
            try {
                val dataResponse = dataService.loadDataBanner()
                if(dataResponse.status == 200){
                    _dataState.postValue(ApiState(State.SUCCESS, dataResponse.data))
                }else{
                    _dataState.postValue(ApiState(State.ERROR, null))
                }
            }catch (ex: Exception){
                Log.e("ruppite", "Error While loading data: $ex")
            }
        }
    }
}