package com.kls.mystoryapps.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kls.mystoryapps.R
import com.kls.mystoryapps.databinding.ActivityLoginBinding
import com.kls.mystoryapps.model.LoginResponse
import com.kls.mystoryapps.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showLoading(false)
        binding.btnSubmitLogin.setOnClickListener {
            showLoading(true)
            loginTask(binding.edtEmail.text.toString(),binding.edtPassword.text.toString())
        }
    }

    private fun loginTask(email: String, password: String){
        val client = ApiConfig.getApiService().loginUserTask(email,password)
        client.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    showToast(response.body()?.message.toString())
                } else {
                    showToast(response.message())
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                showLoading(false)
                Log.e("FAILURE", "onFailure: ${t.message.toString()}")
            }

        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}