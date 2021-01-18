package com.allens.sample_retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<Repo>
}