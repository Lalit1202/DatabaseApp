package com.example.databaseapp.forgetpassword

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.databaseapp.database.RegisterRepository
import com.example.databaseapp.login.LoginViewModel
import com.example.databaseapp.register.RegisterViewModel
import java.lang.IllegalArgumentException

class ChangePassViewModelFactory(
        private  val repository: RegisterRepository,
        private val application: Application): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ChangePassViewModel::class.java)) {
            return ChangePassViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}