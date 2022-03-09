package com.siddydevelops.githubapi_kotlin.ApiModel

data class GithubDetailModel (
    val id: Int,
    val name: String,
    val description: String,
    val forks: Int,
    val visibility: String,
    val watchers: Int
)