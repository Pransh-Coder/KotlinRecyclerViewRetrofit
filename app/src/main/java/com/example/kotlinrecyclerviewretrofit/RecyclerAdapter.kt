package com.example.kotlinrecyclerviewretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val context: Context,var photosList: ArrayList<Photos>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.photos_list,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(photosList.get(position).title)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title:TextView

        init {
            title = itemView.findViewById(R.id.title)
        }


    }

}