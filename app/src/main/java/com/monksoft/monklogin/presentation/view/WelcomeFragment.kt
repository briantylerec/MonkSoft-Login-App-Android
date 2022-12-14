package com.monksoft.monklogin.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.monksoft.monklogin.R
import com.monksoft.monklogin.data.dataBase.entities.UserEntity
import com.monksoft.monklogin.databinding.FragmentWelcomeBinding
import com.monksoft.monklogin.presentation.viewModel.ProtoViewModel
import com.monksoft.monklogin.presentation.viewModel.UserViewModel
import com.monksoft.monklogin.utils.Preferences

class WelcomeFragment : Fragment() {

    private lateinit var binding : FragmentWelcomeBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var userPref : String
    private lateinit var contexto : FragmentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        contexto = FragmentActivity();
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);

        userPref = activity?.let { Preferences(it).getValue().toString() }.toString()

        userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel::class.java)

        //compare if user is first login to greeting
        activity?.let {
            userViewModel.allUsers.observe(it, Observer { users ->
                users.forEach{ user->
                    if (user.username.toString() == userPref.toString()) {

                        if(user.intent == 0 ){
                            binding.tvWelcome.text = "Welcome ${user.username}!"
                            val userEntity = UserEntity(user.username.toString(), 1)
                            userViewModel.updateUser(userEntity)
                            userPref = ""
                        } else {
                            binding.tvWelcome.text = "Hi again ${user.username}!"
                        }
                    }
                }
            })
        }

        binding.bLogout.setOnClickListener {
            activity?.let { it1 -> Preferences(it1).clear() }
            //activity?.getFragmentManager()?.popBackStack();
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            activity?.supportFragmentManager?.popBackStack()
        }

        return binding.root;
    }
}