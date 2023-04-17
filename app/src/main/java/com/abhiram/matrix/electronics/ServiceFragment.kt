package com.abhiram.matrix.electronics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import com.abhiram.matrix.R


class ServiceFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val back : ImageView = view.findViewById(R.id.back)

        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_electronicsFragment)
        }
        val give : ImageView = view.findViewById(R.id.give)

        give.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_giveEFragment)
        }
        val grab : ImageView = view.findViewById(R.id.grab)

        grab.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_grabEFragment)
        }
    }
}