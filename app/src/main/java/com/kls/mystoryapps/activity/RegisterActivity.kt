package com.kls.mystoryapps.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kls.mystoryapps.R
import com.kls.mystoryapps.databinding.ActivityLoginBinding
import com.kls.mystoryapps.databinding.ActivityRegisterBinding
import com.kls.mystoryapps.model.LoginResponse
import com.kls.mystoryapps.model.RegisterResponse
import com.kls.mystoryapps.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showLoading(false)
        binding.btnSubmitLogin.setOnClickListener {
            showLoading(true)
            registerTask(binding.edtNama.text.toString(),binding.edtEmail.text.toString(),binding.edtPassword.text.toString())
        }
    }

    private fun registerTask(nama: String, email: String, password: String){
        val client = ApiConfig.getApiService().registerUserTask(nama,email,password)
        client.enqueue(object: Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    showToast(response.body()?.message.toString())
                } else {
                    showToast(response.message())
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
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