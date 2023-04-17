package com.abhiram.matrix.startup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.abhiram.matrix.R
import com.abhiram.matrix.helpers.SharedPreferncesHelper
import com.google.firebase.auth.FirebaseAuth


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences : SharedPreferncesHelper = SharedPreferncesHelper()
        val auth = FirebaseAuth.getInstance()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        if(sharedPreferences.isFirstRun()){
            Navigation.findNavController(view).navigate(R.id.navigate_to_startupFragment)
        }else{
            if(auth.currentUser==null){
                Toast.makeText(context,"User not Logged in\nPlease Login first!", android.widget.Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.navigate_to_startupFragment)
            }else{
                Navigation.findNavController(view).navigate(R.id.navigate_to_homeFragment)
            }
        }
    }
}