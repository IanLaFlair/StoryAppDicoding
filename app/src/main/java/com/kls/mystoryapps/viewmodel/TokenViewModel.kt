package com.kls.mystoryapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kls.mystoryapps.utils.TokenPreference
import kotlinx.coroutines.launch

class TokenViewModel (private val pref: TokenPreference) : ViewModel() {
    fun getTokens(): LiveData<String?> {
        return pref.getToken().asLiveData()
    }

    fun saveTokens(token: String) {
        viewModelScope.launch {
            pref.saveToken(token)
        }
    }

    fun removeTokens() {
        viewModelScope.launch {
            pref.removeToken()
        }
    }

}