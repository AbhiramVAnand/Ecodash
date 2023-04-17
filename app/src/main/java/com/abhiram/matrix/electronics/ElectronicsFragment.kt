package com.abhiram.matrix.electronics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.Navigation
import com.abhiram.matrix.R
import com.abhiram.matrix.helpers.SharedPreferncesHelper


class ElectronicsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_electronics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val back : ImageView = view.findViewById(R.id.back)
        val sharedPreferences = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        val accessories : LinearLayout = view.findViewById(R.id.accessories)
        val laptop : LinearLayout = view.findViewById(R.id.laptop)
        val camera : LinearLayout = view.findViewById(R.id.camera)

        accessories.setOnClickListener {
            sharedPreferences.setECat("accessories")
            Navigation.findNavController(view).navigate(R.id.navigate_to_serviceFragment)
        }

        camera.setOnClickListener {
            sharedPreferences.setECat("camera")
            Navigation.findNavController(view).navigate(R.id.navigate_to_serviceFragment)
        }

        laptop.setOnClickListener {
            sharedPreferences.setECat("laptop")
            Navigation.findNavController(view).navigate(R.id.navigate_to_serviceFragment)
        }

        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_homeFragment)
        }
    }

}