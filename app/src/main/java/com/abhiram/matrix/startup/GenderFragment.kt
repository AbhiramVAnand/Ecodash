package com.abhiram.matrix.startup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.abhiram.matrix.R
import com.abhiram.matrix.helpers.SharedPreferncesHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class GenderFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gender, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val male : Button = view.findViewById(R.id.male)
        val female : Button = view.findViewById(R.id.female)
        val nonbin : Button = view.findViewById(R.id.nonbin)
        val sharedPreferences : SharedPreferncesHelper = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val uid = auth.uid
        val nameReg = database.getReference("Users/$uid/Name")
        val mailReg = database.getReference("Users/$uid/Mail")
        val numReg = database.getReference("Users/$uid/Contact")
        val genderReg = database.getReference("Users/$uid/Gender")
        male.setOnClickListener {
            nameReg.setValue(sharedPreferences.getName())
            mailReg.setValue(sharedPreferences.getMail())
            numReg.setValue(sharedPreferences.getContact())
            genderReg.setValue("Male")
            Toast.makeText(requireContext(),"Signed up Successfully", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.navigate_to_homeFragment)
            sharedPreferences.setFirstRun()
        }
        female.setOnClickListener {
            nameReg.setValue(sharedPreferences.getName())
            mailReg.setValue(sharedPreferences.getMail())
            numReg.setValue(sharedPreferences.getContact())
            genderReg.setValue("Female")
            Toast.makeText(requireContext(),"Signed up Successfully", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.navigate_to_homeFragment)
            sharedPreferences.setFirstRun()
        }
        nonbin.setOnClickListener {
            nameReg.setValue(sharedPreferences.getName())
            mailReg.setValue(sharedPreferences.getMail())
            numReg.setValue(sharedPreferences.getContact())
            genderReg.setValue("Non-Binary")
            Toast.makeText(requireContext(),"Signed up Successfully", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.navigate_to_homeFragment)
            sharedPreferences.setFirstRun()
        }
    }
}