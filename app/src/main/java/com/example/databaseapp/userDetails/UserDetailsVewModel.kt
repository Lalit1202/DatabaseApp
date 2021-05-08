package com.example.databaseapp.userDetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databaseapp.database.RegisterRepository

class UserDetailsVewModel (private val repository: RegisterRepository, application: Application):
    AndroidViewModel(application){

    val users = repository.users
    init {

    }


    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    fun doneNavigating(){
        _navigateto.value=false
    }

    fun backButtonclicked(){
        _navigateto.value = true
    }

}