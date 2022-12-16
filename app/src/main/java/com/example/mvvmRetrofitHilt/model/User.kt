package com.example.mvvmRetrofitHilt.model

import android.util.Patterns

data class User(
    val mobileNumber: String? = null,
    val otp: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val age: String? = null,
    val type: String? = null,
    val pwd: String? = null
) {
    fun isValidEmail(): Boolean = email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isValidPassword(): Boolean = pwd != null && pwd.length >= 6

    fun isValidEmailPassword(): Boolean = isValidEmail() && isValidPassword()
}