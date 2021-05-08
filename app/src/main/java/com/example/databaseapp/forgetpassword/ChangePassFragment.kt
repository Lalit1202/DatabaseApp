package com.example.databaseapp.forgetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.databaseapp.R
import com.example.databaseapp.database.RegisterDatabase
import com.example.databaseapp.database.RegisterRepository
import com.example.databaseapp.databinding.FragmentChangePassBinding
import com.example.databaseapp.databinding.FragmentRegisterBinding
import com.example.databaseapp.register.RegisterFragmentDirections
import com.example.databaseapp.register.RegisterViewModel
import com.example.databaseapp.register.RegisterViewModelFactory


class ChangePassFragment : Fragment() {

    private lateinit var changePassViewModel: ChangePassViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentChangePassBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_change_pass, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = ChangePassViewModelFactory(repository, application)

        changePassViewModel = ViewModelProvider(this, factory).get(ChangePassViewModel::class.java)

        binding.myViewModel = changePassViewModel

        binding.lifecycleOwner = this

        changePassViewModel.navigateto.observe(this, Observer { hasFinished->
            if (hasFinished == true){

                displayUsersList()
                changePassViewModel.doneNavigating()
            }
        })

        changePassViewModel.userDetailsLiveData.observe(this, Observer {

        })
        changePassViewModel.errotoast.observe(this, Observer { hasChanged ->
            if(hasChanged==true)
            {
                Toast.makeText(requireContext(), "Password Changed Successfully", Toast.LENGTH_SHORT).show()
                changePassViewModel.donetoast()
            }
        })


        changePassViewModel.errotoast.observe(this, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                changePassViewModel.donetoast()
            }
        })



        return binding.root
    }

    private fun displayUsersList() {

        val action = ChangePassFragmentDirections.actionChangePassFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

}

