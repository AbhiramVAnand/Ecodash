package com.abhiram.matrix.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.matrix.R
import com.abhiram.matrix.helpers.SharedPreferncesHelper
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso


lateinit var ind : View
class EItemAdapter(val list : ArrayList<EItem>, val inflate : View, val sharedPreferncesHelper: SharedPreferncesHelper, val context : Context): RecyclerView.Adapter<EItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EItemAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.historylist,parent,false)
        Log.e("History",list.size.toString())
        inf = inflate
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        Picasso.get().load(item.Image).into(holder.image);
        holder.name.text = item.Brand+" "+item.Model
        holder.time.text = "by ${item.Name}"
        holder.contact.setOnClickListener {
            sharedPreferncesHelper.SharedPreferncesHelperInit(context)
            val contact = sharedPreferncesHelper.getContact()
// Use format with "tel:" and phoneNumber created is
            // stored in u.
            // Use format with "tel:" and phoneNumber created is
            // stored in u.
            val u: Uri = Uri.parse("tel:$contact")

            // Create the intent and set the data for the
            // intent as the phone number.

            // Create the intent and set the data for the
            // intent as the phone number.
            val i = Intent(Intent.ACTION_DIAL, u)

            try {
                // Launch the Phone app's dialer with a phone
                // number to dial a call.
                context.startActivity(i)
            } catch (s: SecurityException) {
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val card : LinearLayout = itemview.findViewById(R.id.card)
        var image : ImageView = itemview.findViewById(R.id.itemimage)
        var name : TextView = itemview.findViewById(R.id.itemname)
        var time : TextView = itemview.findViewById(R.id.time)
        val contact : MaterialButton = itemview.findViewById(R.id.contact)
    }
}