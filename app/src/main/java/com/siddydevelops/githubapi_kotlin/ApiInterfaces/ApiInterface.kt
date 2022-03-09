package com.siddydevelops.githubapi_kotlin.ApiInterfaces


import com.siddydevelops.githubapi_kotlin.ApiModel.GithubDetailModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("repos")
    fun getGithubData(): retrofit2.Call<List<GithubDetailModel>>

    companion object {
        const val BASE_URL = "https://api.github.com/orgs/octokit/"

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

}