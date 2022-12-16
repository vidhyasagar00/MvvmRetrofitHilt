package com.example.mvvmRetrofitHilt.networkApi


import com.example.mvvmRetrofitHilt.model.User
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(private val api: Api) {

    suspend fun signIn(mail : String, password : String): Response<String> {
        return api.userLogin(User(email = mail, pwd = password))
    }
}