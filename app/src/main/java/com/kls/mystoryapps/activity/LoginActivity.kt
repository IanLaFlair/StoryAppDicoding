package com.kls.mystoryapps.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.kls.mystoryapps.databinding.ActivityLoginBinding
import com.kls.mystoryapps.model.LoginResponse
import com.kls.mystoryapps.utils.ApiConfig
import com.kls.mystoryapps.utils.TokenPreference
import com.kls.mystoryapps.viewmodel.TokenViewModel
import com.kls.mystoryapps.viewmodel.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userToken")
    private lateinit var tokenViewModel: TokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showLoading(false)

        val pref = TokenPreference.getInstance(dataStore)
        tokenViewModel = ViewModelProvider(this, ViewModelFactory(pref))[TokenViewModel::class.java]
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
                    val token = response.body()?.loginResult?.token.toString()
                    tokenViewModel.saveTokens(token)
                    val intent = Intent(this@LoginActivity,ListStoryActivity::class.java)
                    startActivity(intent)
                    finish()
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