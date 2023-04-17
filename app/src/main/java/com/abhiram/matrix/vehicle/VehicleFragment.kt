package com.abhiram.matrix.vehicle

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


class VehicleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        val back : ImageView = view.findViewById(R.id.back)

        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_homeFragment)
        }

        val bike : LinearLayout = view.findViewById(R.id.bike)

        bike.setOnClickListener {
            sharedPreferences.setVCat("bike")
            Navigation.findNavController(view).navigate(R.id.navigate_to_serviceVFragment)
        }

        val car : LinearLayout = view.findViewById(R.id.car)

        car.setOnClickListener {
            sharedPreferences.setVCat("car")
            Navigation.findNavController(view).navigate(R.id.navigate_to_serviceVFragment)
        }
    }
}