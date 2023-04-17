package com.abhiram.matrix.startup

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.statusBarColor = resources.getColor(R.color.dark)
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val finish : Button = view.findViewById(R.id.Login)
        val back : ImageView = view.findViewById(R.id.back)
        val sharedPreferences : SharedPreferncesHelper = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val ip1 = view.findViewById<EditText?>(R.id.mail)
        val ip2 = view.findViewById<EditText?>(R.id.password)
        ip1.showkeyboard()
        finish.setOnClickListener {
            val mail : String = ip1.text.toString()
            val password : String = ip2.text.toString()
            if(mail.trim().isNotEmpty() && password.trim().isNotEmpty()){
                auth.signInWithEmailAndPassword(mail,password)
                    .addOnCompleteListener {task ->
                        if (task.isSuccessful){
                            val uid = auth.uid
                            Toast.makeText(requireContext(),"Login Successfull",Toast.LENGTH_SHORT).show()
                            val myRef = database.getReference("Users/$uid/Name")
                            // Read from the database
                            myRef.addValueEventListener(object: ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    sharedPreferences.setName(snapshot.value.toString())
                                }
                                override fun onCancelled(error: DatabaseError) {
                                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                                }
                            })
                            val mail = database.getReference("Users/$uid/Mail")
                            // Read from the database
                            mail.addValueEventListener(object: ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    sharedPreferences.setMail(snapshot.value.toString())
                                }
                                override fun onCancelled(error: DatabaseError) {
                                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                                }
                            })
                            val contact = database.getReference("Users/$uid/Contact")
                            // Read from the database
                            contact.addValueEventListener(object: ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    sharedPreferences.setContact(snapshot.value.toString())
                                }
                                override fun onCancelled(error: DatabaseError) {
                                    Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                                }
                            })
                            sharedPreferences.setFirstRun()
                            Navigation.findNavController(view).navigate(R.id.navigate_to_homeFragment)
                        }else{
                            Toast.makeText(requireContext(),"Login Failed!",Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(requireContext(),"Fields can't be empty",Toast.LENGTH_SHORT).show()
            }
        }
        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_startupFragment)
        }
    }
}