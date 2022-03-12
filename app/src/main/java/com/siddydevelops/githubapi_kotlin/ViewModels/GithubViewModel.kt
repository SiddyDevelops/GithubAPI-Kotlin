package com.siddydevelops.githubapi_kotlin.ViewModels

import android.util.Log
import androidx.lifecycle.*
import com.siddydevelops.githubapi_kotlin.ApiInterfaces.ApiInterface
import com.siddydevelops.githubapi_kotlin.ApiModel.GithubDetailModel
import kotlinx.coroutines.launch

class GithubViewModel : ViewModel() {

    private val TAG = "GithubViewModel"
    private val isLoading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<Boolean>()
    private val githubItemList = MutableLiveData<List<GithubDetailModel>>()

    init {
        getGithubList()
    }

    private fun getGithubList() {
        isLoading.value = true
        error.value = false

        viewModelScope.launch {
            try {
                val response = ApiInterface.create().getCoroutineGithubData()
                if(response.isSuccessful && response.body() != null) {
                    val apiList = response.body()!!
                    isLoading.value = false
                    githubItemList.value = apiList
                } else {
                    Log.d(TAG,"ERROR: ${response.message()}")
                    error.value = true
                }
            } catch(e: Exception) {
                error.value = true
                Log.d(TAG,"ERROR: ${e.message}")
            }
        }
    }

    fun getGithubItemsInList(): LiveData<List<GithubDetailModel>> {
        return githubItemList
    }

    fun getLoading(): LiveData<Boolean>{
        return isLoading
    }

    fun getError(): LiveData<Boolean>{
        return error
    }

}