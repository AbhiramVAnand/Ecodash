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

class MailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences : SharedPreferncesHelper = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        val input = view.findViewById<EditText>(R.id.mail)
        val back : ImageView = view.findViewById(R.id.back)
        val next : Button = view.findViewById(R.id.next)
        input.showkeyboard()
        next.setOnClickListener{
            val mail : String = input.text.toString()
            if(mail.trim().isEmpty()){
                Toast.makeText(requireContext(),"Field can't be blank", Toast.LENGTH_SHORT).show()
            }else{
                sharedPreferences.setMail(mail.trim())
                Navigation.findNavController(view).navigate(R.id.navigate_to_contactFragment)
            }
        }
        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_nameFragment)
        }
    }
}