package com.abhiram.matrix.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.abhiram.matrix.R
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso

class HistoryAdapter(private val mlist : ArrayList<Items>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.historylist,parent,false)
        Log.e("History",mlist.size.toString())
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        val item = mlist[position]
        Picasso.get().load(item.image).into(holder.image);
        holder.name.text = item.name
        holder.time.text = item.time
        holder.contact.isVisible = false
    }

    override fun getItemCount(): Int {
        return mlist.size
    }
    class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        var image : ImageView = itemview.findViewById(R.id.itemimage)
        var name : TextView = itemview.findViewById(R.id.itemname)
        var time : TextView = itemview.findViewById(R.id.time)
        val contact : MaterialButton = itemview.findViewById(R.id.contact)

    }
}