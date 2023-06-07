package com.example.BuSUS

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OniAdapter(private val oniList: ArrayList<ItinerarioModelo>) :
    RecyclerView.Adapter<OniAdapter.ViewHolder>() {

   // private lateinit var mListener: onItemClickListener

//    interface onItemClickListener{
//        fun onItemClick(position: Int)
//    }
//
//    fun setOnItemClickListener(clickListener: onItemClickListener){
//        mListener = clickListener
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.oni_list_item, parent, false)
        return ViewHolder(itemView)
        //return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = oniList[position]
        holder.tvOniName.text = currentItem.oniData
        holder.tvOniCity.text = currentItem.oniCidade
        holder.tvOniExame.text = currentItem.oniExame
    }

    override fun getItemCount(): Int {
        return oniList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvOniName : TextView = itemView.findViewById(R.id.tvOniData)
        val tvOniCity: TextView = itemView.findViewById(R.id.tvOniCity)
        val tvOniExame: TextView = itemView.findViewById(R.id.tvOniExame)


    }
//    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
//
//        val tvEmpName : TextView = itemView.findViewById(R.id.tvEmpName)
//
//        init {
//            itemView.setOnClickListener {
//                clickListener.onItemClick(adapterPosition)
//            }
//        }
//
//    }

}