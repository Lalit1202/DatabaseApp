package com.example.databaseapp.userDetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.databaseapp.database.RegisterRepository
import java.lang.IllegalArgumentException

class UserDetailsFactory (
    private  val repository: RegisterRepository,
    private val application: Application
): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserDetailsVewModel::class.java)) {
            return UserDetailsVewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
