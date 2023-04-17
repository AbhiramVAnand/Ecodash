package com.abhiram.matrix.startup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.Navigation
import com.abhiram.matrix.R
import com.abhiram.matrix.helpers.SharedPreferncesHelper
import com.abhiram.matrix.helpers.showkeyboard

class ContactFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences : SharedPreferncesHelper = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        val back : ImageView = view.findViewById(R.id.back)
        val next : Button = view.findViewById(R.id.next)
        val input =  view.findViewById<EditText>(R.id.number)
        input.showkeyboard()
        next.setOnClickListener{
            val number : String = input.text.toString()
            if(number.trim().isNotEmpty()&&number.length==13){
                sharedPreferences.setContact(number.trim())
                Navigation.findNavController(view).navigate(R.id.navigate_to_passwordFragment)
            }else if(number.length!=13){
                Toast.makeText(requireContext(),"Enter a valid phone number", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Field can't be blank", Toast.LENGTH_SHORT).show()
            }
        }
        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_mailFragment)
        }
    }
}