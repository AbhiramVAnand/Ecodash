package com.abhiram.matrix.fragments

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.matrix.R
import com.abhiram.matrix.adapter.HistoryAdapter
import com.abhiram.matrix.adapter.Items
import com.abhiram.matrix.helpers.SharedPreferncesHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class HomeFragment : Fragment() {
    private var history : ArrayList<Items> = ArrayList()
    var json : String = String()
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        read()
    }

    override fun onResume() {
        super.onResume()
        read()
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        read()
        val inflate = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerview : RecyclerView = inflate.findViewById(R.id.recyclerview)
        val load : ImageView = inflate.findViewById(R.id.load)
        val adapter = HistoryAdapter(history)
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
        val sharedPreferences = SharedPreferncesHelper()
        sharedPreferences.SharedPreferncesHelperInit(requireContext())
        val name = view.findViewById<TextView>(R.id.name)
        name.text = sharedPreferences.getName()
        name.setOnClickListener {
            read()
            Log.e("History",history.size.toString())
        }
        val veh : LinearLayout = view.findViewById(R.id.vechicle)
        val ele : LinearLayout = view.findViewById(R.id.electronics)

        veh.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_vehicleFragment)
        }
        ele.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigate_to_electronicsFragment)
        }
    }
    fun read(){
        val database = Firebase.database
        val uid = FirebaseAuth.getInstance().uid
        val path = "History/$uid"
        val gson : Gson = Gson()
        val mail = database.getReference(path)
        // Read from the database
        mail.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    val file : Map<String,String> = i.value as Map<String, String>
                    json = gson.toJson(file)
                    val temp : Items = gson.fromJson(json, Items::class.java)
                    history.add(temp)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }
}