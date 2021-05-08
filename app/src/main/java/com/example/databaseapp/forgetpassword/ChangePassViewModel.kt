 package com.example.databaseapp.forgetpassword

 import android.app.Application
 import android.util.Log
 import androidx.databinding.Bindable
 import androidx.databinding.Observable
 import androidx.lifecycle.*
 import com.example.databaseapp.database.RegisterEntity
 import com.example.databaseapp.database.RegisterRepository
 import kotlinx.coroutines.*


 class ChangePassViewModel(private val repository: RegisterRepository, application: Application) :
         AndroidViewModel(application), Observable {

     init {

     }


     private var userdata: String? = null

     var userDetailsLiveData = MutableLiveData<Array<RegisterEntity>>()


     @Bindable
     val inputUsername = MutableLiveData<String>()

     @Bindable
     val inputNewPassword = MutableLiveData<String>()

     private val viewModelJob = Job()
     private val uiScope = CoroutineScope(Dispatchers.Main+ viewModelJob)


     private val _navigateto = MutableLiveData<Boolean>()

     val navigateto: LiveData<Boolean>
         get() = _navigateto

     private val _errorToast = MutableLiveData<Boolean>()

     val errotoast: LiveData<Boolean>
         get() = _errorToast

     private val _errorToastUsername = MutableLiveData<Boolean>()

     val errotoastUsername: LiveData<Boolean>
         get() = _errorToastUsername


     fun sumbitButton() {

         if (  inputUsername.value == null || inputNewPassword.value == null) {
             _errorToast.value = true
         } else {
             uiScope.launch {
//            withContext(Dispatchers.IO) {
                 val usersNames = repository.getUserName(inputUsername.value!!)
                 val firstName = usersNames?.firstName.toString()
                 val lastName = usersNames?.lastName.toString()
                 val email = usersNames?.userName.toString()
                 val id = usersNames?.userId as Int

                 if (usersNames != null) {
                     val password = inputNewPassword.value!!
                     update(RegisterEntity(id,firstName,lastName,email,password))

                     inputUsername.value = null
                     inputNewPassword.value = null
                     _errorToast.value = true
                     _navigateto.value = true

                 }
             }
         }
     }


     override fun onCleared() {
         super.onCleared()
     }

     fun doneNavigating() {
         _navigateto.value = false

     }

     fun donetoast() {
         _errorToast.value = false

     }

     fun donetoastUserName() {
         _errorToast.value = false

     }

     private fun update(user: RegisterEntity): Job = viewModelScope.launch {
         repository.update(user)
     }


     override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

     }

     override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

     }

 }



