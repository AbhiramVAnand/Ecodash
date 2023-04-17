package com.abhiram.matrix.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.abhiram.matrix.R

class ServiceVFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_servicev, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val back : ImageView = view.findViewById(R.id.back)

        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_vehicleFragment)
        }
        val give : ImageView = view.findViewById(R.id.givev)

        give.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_giveFragment)
        }
        val grab : ImageView = view.findViewById(R.id.grabv)

        grab.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_grabFragment)
        }
        val lift : ImageView = view.findViewById(R.id.liftv)

        lift.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_grabLFragment)
        }
    }

}