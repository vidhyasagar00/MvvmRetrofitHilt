package com.example.mvvmRetrofitHilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mvvmRetrofitHilt.databinding.ActivityMainBinding
import com.example.mvvmRetrofitHilt.networkApi.ApiListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = loginViewModel
        }

        signInListener()
    }

    private fun signInListener() {
        loginViewModel.signInListener = object : ApiListener<String> {
            override fun onLoading() {
                Toast.makeText(this@MainActivity, "loading", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(response: String?) {
                Toast.makeText(this@MainActivity, "login Success", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(message: String) {
                Toast.makeText(this@MainActivity, "login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}