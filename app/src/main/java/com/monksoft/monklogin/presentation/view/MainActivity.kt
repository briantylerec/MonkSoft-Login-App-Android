package com.monksoft.monklogin.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.monksoft.monklogin.R
import com.monksoft.monklogin.databinding.ActivityMainBinding
import com.monksoft.monklogin.presentation.viewModel.ProtoViewModel
import com.monksoft.monklogin.presentation.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var protoViewModel : ProtoViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //view-models instances
        protoViewModel = ViewModelProvider(this).get(ProtoViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, LoginFragment())
            //.addToBackStack("LoginFragment")
            //.addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //supportFragmentManager.popBackStack()
    }
}