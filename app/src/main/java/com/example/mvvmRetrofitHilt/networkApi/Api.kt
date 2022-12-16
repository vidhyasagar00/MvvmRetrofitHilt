package com.example.mvvmRetrofitHilt.networkApi

import com.example.mvvmRetrofitHilt.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("api/auth/login")
    suspend fun userLogin(
        @Body
        user: User
    ): Response<String>

}