package com.siddydevelops.githubapi_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.siddydevelops.githubapi_kotlin.ApiInterfaces.ApiInterface
import com.siddydevelops.githubapi_kotlin.ApiModel.GithubDetailModel
import com.siddydevelops.githubapi_kotlin.RV.RVAdapter
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewMain)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch data from API using conventional Retrofit-Enqueue Method
        /* val apiInterface = ApiInterface.create().getGithubData()
        apiInterface.enqueue(object: Callback<List<GithubDetailModel>>{
            override fun onResponse(call: Call<List<GithubDetailModel>>,response: Response<List<GithubDetailModel>>) {
                if(response.body() != null) {
                    Log.d(TAG, "data: ${response.body()}")
                    for(item: GithubDetailModel in response.body()!!) {
                        Log.d(TAG, "Item: ${item.forks}")
                    }
                    val adapter = RVAdapter(response.body()!!)
                    recyclerView.adapter = adapter
                    progressBar.visibility = View.INVISIBLE
                }
            }

            override fun onFailure(call: Call<List<GithubDetailModel>>, t: Throwable) {
                Log.d(TAG, "ERROR: ${t.message}")
            }
        }) */

        // Fetching data from API using COROUTINES
        launch(Dispatchers.Main) {
            try {
                val response = ApiInterface.create().getCoroutineGithubData()
                if(response.isSuccessful && response.body() != null) {
                    val apiList = response.body()!!
                    val adapter = RVAdapter(apiList)
                    recyclerView.adapter = adapter
                    progressBar.visibility = View.INVISIBLE
                } else {
                    Log.d(TAG,"ERROR: ${response.message()}")
                    Toast.makeText(this@MainActivity,"Error Occurred: ${response.message()}",Toast.LENGTH_SHORT).show()
                }
            } catch(e: Exception) {
                Toast.makeText(this@MainActivity,"Error Occurred: ${e.message}",Toast.LENGTH_SHORT).show()
                Log.d(TAG,"ERROR: ${e.message}")
            }
        }
    }
}