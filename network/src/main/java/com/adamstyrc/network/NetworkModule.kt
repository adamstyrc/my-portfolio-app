package com.adamstyrc.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Reusable
    fun provideGithubApi(okHttpClient: OkHttpClient ): GithubApi {
        return Retrofit.Builder()
            .baseUrl(GithubApi.HOST_ADDRESS)
//            .callFactory { OkHttpClient.Builder().build().newCall(it) }
            .callFactory { okHttpClient.newCall(it) }
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(GithubApi::class.java)
    }
}