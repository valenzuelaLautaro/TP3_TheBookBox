package com.tp3.tp3_thebookbox.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.tp3.tp3_thebookbox.databinding.FragmentGoogleLoginBinding





class GoogleLoginFragment : Fragment() {

    //private lateinit var viewModel: GoogleLoginViewModel
    private lateinit var binding: FragmentGoogleLoginBinding
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoogleLoginBinding.inflate(inflater, container, false)

        googleSetup()
        return binding.root
    }


    fun googleSetup(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())

        binding.btnlogin.setOnClickListener {
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()

            signInLauncher.launch(signInIntent)
        }

    }


    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode ==  Activity.RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            // ...
            //val action = GoogleLoginFragmentDirections.actionGoogleLoginToHomeFragment2(user)
            if (user!=null){
                val action = GoogleLoginFragmentDirections.actionGoogleLoginToMainActivity(user)
                binding.root.findNavController().navigate(action)
            }else{
                println("usuario null ")
            }

        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }



}