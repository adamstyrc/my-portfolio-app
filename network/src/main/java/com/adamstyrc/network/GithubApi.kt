package com.adamstyrc.network

import com.adamstyrc.network.model.UserDescription
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface GithubApi {

    @GET("user-description.json")
    suspend fun getUserDescription(): Response<UserDescription>

    companion object {
        const val HOST_ADDRESS = "https://raw.githubusercontent.com/adamstyrc/my-portfolio-app/master/mock-data/"
    }
}