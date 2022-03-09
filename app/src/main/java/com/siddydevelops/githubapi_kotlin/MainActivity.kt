package com.siddydevelops.githubapi_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.siddydevelops.githubapi_kotlin.ApiInterfaces.ApiInterface
import com.siddydevelops.githubapi_kotlin.ApiModel.GithubDetailModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = ApiInterface.create().getGithubData()
        apiInterface.enqueue(object: Callback<List<GithubDetailModel>>{
            override fun onResponse(call: Call<List<GithubDetailModel>>,response: Response<List<GithubDetailModel>>) {
                if(response.body() != null) {
                    Log.d(TAG, "data: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<GithubDetailModel>>, t: Throwable) {
                Log.d(TAG, "ERROR: ${t.message}")
            }
        })

    }
}