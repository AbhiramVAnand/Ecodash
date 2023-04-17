package com.abhiram.matrix.electronics

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.abhiram.matrix.R
import com.abhiram.matrix.helpers.SharedPreferncesHelper
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class GiveEFragment : Fragment() {
    lateinit var type : String
    lateinit var email : String
    var count : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_give_e, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = Firebase.database
        val uid = FirebaseAuth.getInstance().uid
        val sharedPreferences = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        email = sharedPreferences.getMail()
        val name = sharedPreferences.getName()
        val cat = sharedPreferences.getECat()
        val back : ImageView = view.findViewById(R.id.back)

        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_electronicsFragment)
        }
        val brand : EditText = view.findViewById(R.id.brand)
        val model : EditText = view.findViewById(R.id.model)
        val period : EditText = view.findViewById(R.id.period)
        val accType : EditText = view.findViewById(R.id.type)
        val acTV : TextView = view.findViewById(R.id.actype)
        val desp : TextView = view.findViewById(R.id.descp)
        acTV.isVisible = false
        accType.isVisible = false
        if(cat=="accessories"){
            acTV.isVisible = true
            accType.isVisible = true
            desp.text = "Description"
            model.hint = "Description"
        }

        val addPhoto : Button = view.findViewById(R.id.addpic)
        val submit : MaterialButton = view.findViewById(R.id.share)

        val contact = database.getReference("Electronics/$cat")
//        addPhoto.setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.navigate_to_pickImageFragment)
//        }
        // Read from the database

        count = sharedPreferences.getNum()
        val modelReg = database.getReference("Electronics/$cat/item${count+1}/Model")
        val nameReg = database.getReference("Electronics/$cat/item${count+1}/Name")
        val brandReg = database.getReference("Electronics/$cat/item${count+1}/Brand")
        val periodReg = database.getReference("Electronics/$cat/item${count+1}/Period")
        val uidReg = database.getReference("Electronics/$cat/item${count+1}/Uid")
        val accReg = database.getReference("Electronics/$cat/item${count+1}/Type")
        val isTaken = database.getReference("Electronics/$cat/item${count+1}/IsTaken")

        submit.setOnClickListener {

            val bname : String = brand.text.toString()
            val mname : String = model.text.toString()
            val priod : String = period.text.toString()
            if (cat=="accessories"){
                type = accType.text.toString()
            }else{
                type = "NULL"
            }
            if (bname.trim().isNotEmpty()&&
                    mname.trim().isNotEmpty()&&
                    priod.trim().isNotEmpty()){
                nameReg.setValue(name)
                uidReg.setValue(uid)
                brandReg.setValue(bname)
                modelReg.setValue(mname)
                periodReg.setValue(priod)
                isTaken.setValue(false)
                if (type.trim().isNotEmpty()){
                    accReg.setValue(type)
                }
                Toast.makeText(requireContext(),"Your product has been listed for sharing",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Fields can't be empty!",Toast.LENGTH_SHORT).show()
            }
            count++
            sharedPreferences.setNum(count)
        }
    }
}