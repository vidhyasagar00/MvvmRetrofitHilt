package com.example.mvvmRetrofitHilt.networkApi

interface ApiListener<T> {
    fun onLoading()
    fun onSuccess(response: T?)
    fun onFailure(message: String)
}