package com.example.mvvmRetrofitHilt.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmRetrofitHilt.model.User
import com.example.mvvmRetrofitHilt.networkApi.ApiListener
import com.example.mvvmRetrofitHilt.networkApi.ApiRepository
import com.example.mvvmRetrofitHilt.utils.Coroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    var email : MutableLiveData<String> = MutableLiveData("test@gmail.com")
    var password : MutableLiveData<String> = MutableLiveData("qweqwe")

    var signInListener: ApiListener<String>? = null

    fun signIn() {
        val currentUser = User(email = email.value, pwd = password.value)
        signInListener?.onLoading()
        if(!currentUser.isValidEmailPassword()) {
            signInListener?.onFailure("Invalid email or password")
            return
        }
        Coroutines.main {
            try {
                val authResponse = repository.signIn(currentUser.email!!, currentUser.pwd!!)
                if (authResponse.isSuccessful) {
                    authResponse.body().let {
                        signInListener?.onSuccess(it as String)
                        return@main
                    }
                }
                signInListener?.onFailure(authResponse.code().toString())
            } catch (e: Exception) {
                signInListener?.onFailure(e.message!!)
            }
        }
    }
}