package com.abhiram.matrix.startup

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import com.abhiram.matrix.R
import com.abhiram.matrix.helpers.SharedPreferncesHelper
import com.abhiram.matrix.helpers.showkeyboard


class NameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name, container, false)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().window.statusBarColor = resources.getColor(R.color.dark)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences : SharedPreferncesHelper = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        val back : ImageView = view.findViewById(R.id.back)
        val next : Button = view.findViewById(R.id.next)
        val input =  view.findViewById<EditText>(R.id.name)
        input.showkeyboard()
        next.setOnClickListener{
            val name : String = input.text.toString()
            if(name.trim().isEmpty()){
                Toast.makeText(requireContext(),"Field can't be blank",Toast.LENGTH_SHORT).show()
            }else{
                sharedPreferences.setName(name.trim())
                Navigation.findNavController(view).navigate(R.id.navigate_to_mailFragment)
            }
        }
        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_startupFragment)
        }
    }
}