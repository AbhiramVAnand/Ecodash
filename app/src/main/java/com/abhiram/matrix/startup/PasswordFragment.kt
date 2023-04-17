package com.abhiram.matrix.startup

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.Navigation
import com.abhiram.matrix.R
import com.abhiram.matrix.helpers.SharedPreferncesHelper
import com.abhiram.matrix.helpers.showkeyboard
import com.google.android.material.datepicker.MaterialTextInputPicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PasswordFragment : Fragment() {

    private var i =0
    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences : SharedPreferncesHelper = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val pass : EditText = view.findViewById(R.id.pass)
        val back : ImageView = view.findViewById(R.id.back)
        val next : Button = view.findViewById(R.id.next)
        val mail = sharedPreferences.getMail()
        val name = sharedPreferences.getName()
        val progressBar : ProgressBar = view.findViewById(R.id.progressbar)
        progressBar.visibility = View.INVISIBLE
        pass.showkeyboard()
        next.setOnClickListener{
            val password = pass.text.toString()

            progressBar.visibility = View.VISIBLE

            i = progressBar.progress

            Thread(Runnable {
                // this loop will run until the value of i becomes 99
                while (i < 100) {
                    i += 1
                    // Update the progress bar and display the current value
                    handler.post(Runnable {
                        progressBar.progress = i
                        // setting current progress to the textview
                    })
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }

                // setting the visibility of the progressbar to invisible
                // or you can use View.GONE instead of invisible
                // View.GONE will remove the progressbar
                progressBar.visibility = View.INVISIBLE

            }).start()

            if(password.trim().isNotEmpty()){
                auth.createUserWithEmailAndPassword(mail,password)
                    .addOnCompleteListener {task ->
                        if(task.isSuccessful){
                            val uid = auth.uid
                            val nameReg = database.getReference("Users/$uid/Name")
                            nameReg.setValue(name)
                            sendEmail(mail)
                            Navigation.findNavController(view).navigate(R.id.navigate_to_genderFragment)
                        }else if(auth.currentUser!=null){
                            Toast.makeText(requireContext(),"Account exits, Please login",Toast.LENGTH_LONG).show()
                            Navigation.findNavController(view).navigate(R.id.navigate_to_startupFragment)
                        }else{
                            Toast.makeText(requireContext(),"Sign Up Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(requireContext(),"Field can't be blank", Toast.LENGTH_SHORT).show()
            }
        }
        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_contactFragment)
        }

    }
    fun sendEmail(mail : String){
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        user?.let{
            it.sendEmailVerification().addOnCompleteListener{task ->
                if (task.isSuccessful)
                    Toast.makeText(requireContext(),"Verification mail send to $mail",Toast.LENGTH_LONG).show()
            }
        }
    }
}