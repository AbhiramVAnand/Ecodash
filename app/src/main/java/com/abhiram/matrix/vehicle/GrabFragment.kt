package com.abhiram.matrix.vehicle

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.matrix.R
import com.abhiram.matrix.adapter.GiveItem
import com.abhiram.matrix.adapter.HistoryAdapter
import com.abhiram.matrix.adapter.ItemAdapter
import com.abhiram.matrix.adapter.Items
import com.abhiram.matrix.helpers.SharedPreferncesHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson


class GrabFragment : Fragment() {
    private var list : ArrayList<GiveItem> = ArrayList()

    var json : String = String()
    var cat : String = String()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_grabv, container, false)
        read()
        val sharedPreferences = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        val recyclerview : RecyclerView = inflate.findViewById(R.id.rcvitem)
        val load : ImageView = inflate.findViewById(R.id.load)
        val adapter = ItemAdapter(list,inflate, sharedPreferences,requireContext())
        load.setOnClickListener {
            read()
            recyclerview.layoutManager = LinearLayoutManager(context)
            recyclerview.adapter = adapter
            recyclerview.requestLayout()
        }
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun read(){
        val database = Firebase.database
        val uid = FirebaseAuth.getInstance().uid

        val sharedPreferences = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        cat = sharedPreferences.getVCat()
        val path = "Vehicles/$cat/Give"
        Log.e("Cta",cat)
        val gson : Gson = Gson()
        val mail = database.getReference(path)
        // Read from the database
        mail.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    val file : Map<String,String> = i.value as Map<String, String>
                    json = gson.toJson(file)

                    val temp : GiveItem? = gson.fromJson(json, GiveItem::class.java)
                    Log.e("List",i.toString())
                    if (temp != null) {
                        list.add(temp)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }
}