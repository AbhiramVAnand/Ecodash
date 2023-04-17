package com.abhiram.matrix.startup

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.abhiram.matrix.R
import com.google.android.material.button.MaterialButton


class StartupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.statusBarColor = resources.getColor(R.color.splash)
        return inflater.inflate(R.layout.fragment_startup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signin : MaterialButton = view.findViewById(R.id.sigin)
        val signup : MaterialButton = view.findViewById(R.id.signup)

        signin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_signInFragment)
        }
        signup.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_nameFragment)
        }
    }

}