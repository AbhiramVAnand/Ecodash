package com.abhiram.matrix.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.abhiram.matrix.R

class GiveFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_give, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val back : ImageView = view.findViewById(R.id.back)

        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_serviceVFragment)
        }
        val give : ImageView = view.findViewById(R.id.give)

        give.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_giveVFragment)
        }
        val lift : ImageView = view.findViewById(R.id.givel)

        lift.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_liftFragment)
        }
    }
}