package com.siddydevelops.githubapi_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.siddydevelops.githubapi_kotlin.rV.RVAdapter
import com.siddydevelops.githubapi_kotlin.viewModels.GithubViewModel
import com.siddydevelops.githubapi_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope(), LifecycleOwner {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    private var viewModel:GithubViewModel? = null
    private lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)

        adapter = RVAdapter(listOf())
        binding.recyclerViewMain.adapter = adapter

        viewModel = ViewModelProvider(this).get(GithubViewModel::class.java)

        viewModel?.getError()?.observe(this, Observer {
            if(it) {
                Toast.makeText(this, "An error has occurred while downloading data. Please try again.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel?.getLoading()?.observe(this, Observer {
            if(it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel?.getGithubItemsInList()?.observe(this, Observer {
            Log.d(TAG, it.toString())

            adapter.setGithubItems(it)

        })



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
//        lifecycleScope.launch(Dispatchers.IO) {
//            try {
//                val response = ApiInterface.create().getCoroutineGithubData()
//                if(response.isSuccessful && response.body() != null) {
//                    val apiList = response.body()!!
//                    withContext(Dispatchers.Main) {
//                        val adapter = RVAdapter(apiList)
//                        recyclerView.adapter = adapter
//                        progressBar.visibility = View.INVISIBLE
//                    }
//                } else {
//                    Log.d(TAG,"ERROR: ${response.message()}")
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(this@MainActivity,"Error Occurred: ${response.message()}",Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } catch(e: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(this@MainActivity,"Error Occurred: ${e.message}",Toast.LENGTH_SHORT).show()
//                }
//                Log.d(TAG,"ERROR: ${e.message}")
//            }
//        }


//            try {
//                runBlocking {
//                    lifecycleScope.launch(Dispatchers.Main) {
//                        val job1 = async {
//                            for (i in 0..10) {
//                                delay(1500)
//                                Log.d(TAG, "$i")
//                            }
//                            return@async 1
//                        }
//                        val job2 = async {
//                            for (i in 10..15) {
//                                delay(1500)
//                                Log.d(TAG, "$i")
//                            }
//                            return@async 2
//                        }
//
//                        Log.d(TAG, "FINISHED: ${job1.await() + job2.await()}")
//                    }
//
//                }

//                val response = ApiInterface.create().getCoroutineGithubData()
//
//                if (response.isSuccessful && response.body() != null) {
//                    val apiList = response.body()!!
//                    val adapter = RVAdapter(apiList)
//                    recyclerView.adapter = adapter
//                    progressBar.visibility = View.INVISIBLE
//                } else {
//                    Log.d(TAG, "ERROR: ${response.message()}")
//                    Toast.makeText(
//                        this@MainActivity,
//                        "Error Occurred: ${response.message()}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            } catch (e: Exception) {
//                Toast.makeText(
//                    this@MainActivity,
//                    "Error Occurred: ${e.message}",
//                    Toast.LENGTH_SHORT
//                ).show()
//                Log.d(TAG, "ERROR: ${e.message}")
//            }


//        val intent = Intent(this@MainActivity,NewActivity::class.java)
//        startActivity(intent)
//        //finish()

    }

}