package com.abhiram.matrix.vehicle

import android.content.ContentValues
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
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class LiftFragment : Fragment() {
    lateinit var email : String
    var count : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lift, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = Firebase.database
        val uid = FirebaseAuth.getInstance().uid
        val sharedPreferences = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        email = sharedPreferences.getMail()
        val name = sharedPreferences.getName()
        val cat = sharedPreferences.getVCat()
        val back : ImageView = view.findViewById(R.id.back)

        back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_giveFragment)
        }

        val vehmodel : EditText = view.findViewById(R.id.vehmodel)
        val descp : EditText = view.findViewById(R.id.descp)
        val toET : EditText = view.findViewById(R.id.to)
        val fromET : EditText = view.findViewById(R.id.from)
        val regno : EditText = view.findViewById(R.id.regno)
        val addPhoto : Button = view.findViewById(R.id.addpic)
        val submit : MaterialButton = view.findViewById(R.id.share)

        count = sharedPreferences.getNum()
        val modelReg = database.getReference("Vehicles/$cat/Lift/item${count+1}/VehicleModel")
        val nameReg = database.getReference("Vehicles/$cat/Lift/item${count+1}/Name")
        val regNo = database.getReference("Vehicles/$cat/Lift/item${count+1}/Regno")
        val fromReg = database.getReference("Vehicles/$cat/Lift/item${count+1}/From")
        val toReg = database.getReference("Vehicles/$cat/Lift/item${count+1}/To")
        val uidReg = database.getReference("Vehicles/$cat/Lift/item${count+1}/Uid")
        val descpn = database.getReference("Vehicles/$cat/Lift/item${count+1}/Description")
        val isTaken = database.getReference("Vehicles/$cat/Lift/item${count+1}/IsTaken")


        submit.setOnClickListener {

            val mname : String = vehmodel.text.toString()
            val desp : String = descp.text.toString()
            val from : String = fromET.text.toString()
            val to : String = toET.text.toString()
            val reno : String = regno.text.toString()
            if (desp.trim().isNotEmpty()&&
                mname.trim().isNotEmpty()&&
                from.trim().isNotEmpty()&&
                to.trim().isNotEmpty()&&
                reno.trim().isNotEmpty()){
                modelReg.setValue(mname)
                nameReg.setValue(name)
                regNo.setValue(reno)
                fromReg.setValue(from)
                toReg.setValue(to)
                uidReg.setValue(uid)
                descpn.setValue(desp)
                isTaken.setValue(false)
                Toast.makeText(requireContext(),"Your details has been recorded for sharing", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Fields can't be empty!", Toast.LENGTH_SHORT).show()
            }
            count++
            sharedPreferences.setNum(count)
        }
        count++
        sharedPreferences.setNum(count)
    }
}