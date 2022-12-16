package com.monksoft.monklogin.presentation.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import com.monksoft.monklogin.R
import com.monksoft.monklogin.data.dataBase.entities.UserEntity
import com.monksoft.monklogin.databinding.FragmentLoginBinding
import com.monksoft.monklogin.presentation.viewModel.ProtoViewModel
import com.monksoft.monklogin.presentation.viewModel.UserViewModel
import com.monksoft.monklogin.utils.Constants
import com.monksoft.monklogin.utils.EncryptDecrypt
import com.monksoft.monklogin.utils.Preferences
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var contexto : FragmentActivity
    private lateinit var globalUsername : String
    private lateinit var globalMonkcode : String

    private lateinit var protoViewModel : ProtoViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        contexto = FragmentActivity();
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        protoViewModel = ViewModelProviders.of(requireActivity()).get(ProtoViewModel::class.java)
        userViewModel = ViewModelProviders.of(requireActivity()).get(UserViewModel::class.java)

        //show a loading while c++ process is working on
        activity?.let {
            protoViewModel.isLoading.observe(it, Observer { status ->
                binding.progress.isVisible = status
            })
        }

        //function to onClick methods
        onClickListeners()

        return binding.root;
    }

    //to clean all input text when you go close session
    override fun onResume() {
        super.onResume()
        binding.tilCode.editText?.setText("")
        binding.tilName.editText?.setText("")
    }

    private fun onClickListeners(){
        binding.bLogin.setOnClickListener {

            globalUsername = binding.tieName.text.toString()
            globalMonkcode = binding.tieCode.text.toString()

            if ( validator() ) {

                val encryptedMonkcode = encryptData(globalMonkcode)
                val response = protoViewModel.saveEkucode(encryptedMonkcode)

                response.invokeOnCompletion {
                    openHome()
                }
            }
        }
    }

    private fun openHome() {

        if(sendMonkCode()) {

            lifecycleScope.launch {
                val result = async {
                    userViewModel.getUser(globalUsername).observe(viewLifecycleOwner, Observer { usr ->
                        val userEntity = UserEntity(globalUsername, if ( usr != null ) 1 else 0)
                        userViewModel.insertUser(userEntity)

                        //save to sharedPreferences
                        activity?.let { Preferences(it).addValue(globalUsername) }

                        val fragmentManager: FragmentManager? = activity?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()

                        fragmentTransaction?.replace(R.id.fragment_container, WelcomeFragment(), "WelcomeFragment")
                            ?.addToBackStack("WelcomeFragment")
                            ?.commit()
                    })
                }
            }
        } else {
                binding.tilCode.editText?.setText("")
                showDialog()
        }
    }

    private fun encryptData( data : String) = EncryptDecrypt.encrypt(data, Constants.ENCRYPT_DECRYPT_KEY)

    private fun validator(): Boolean {
        if (globalUsername.isNotEmpty()) {
            when (globalMonkcode.length){
                8 -> return true
                0 -> binding.tieCode.run {
                    error = "Insert your Eku-code"
                    requestFocus()
                }
                else -> binding.tieCode.run {
                    error = "The Eku-code is an 8 letter word"
                    requestFocus()
                }
            }
        } else {
            binding.tieName.run {
                error = "Insert your username"
                requestFocus()
            }
        }
        return false
    }

    private fun showDialog(){
//        activity?.let {
//            AlertDialog.Builder(it)
//                .setTitle("¡ Ekucode incorrect !")
//                .setMessage("The entered Ekucode is incorrect, please try again.")
//                .setPositiveButton("Close",
//                    DialogInterface.OnClickListener { dialog, _ ->
//                        dialog.dismiss()
//                    })
//                .setCancelable(true)
//                .show()
//        }

        val dialogo = DialogFragment("¡ Ekucode incorrect !", "The entered Ekucode is incorrect, please try again.", "Accept")
        dialogo.show(parentFragmentManager, "dialog")
    }

    private external fun sendMonkCode(): Boolean

    companion object {
        init { System.loadLibrary("monklogin") }
    }
}