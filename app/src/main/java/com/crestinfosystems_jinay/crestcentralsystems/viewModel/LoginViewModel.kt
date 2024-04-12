package com.crestinfosystems_jinay.crestcentralsystems.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crestinfosystems_jinay.crestcentralsystems.Network.AuthApi


class LoginViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun SignIn(onSuccess: (Map<String, Any>?) -> Unit) {
        Log.d("Email", email.value.toString())
        Log.d("Password", password.value.toString())
        AuthApi.login(email = email.value.toString(),
            password = password.value.toString(),
            onError = {},
            onSuccess = {
                onSuccess(it)
            }
        )
    }
}