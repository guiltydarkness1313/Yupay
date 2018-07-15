package com.shibuyaxpress.yupay.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shibuyaxpress.yupay.Holders.ClothHolder
import com.shibuyaxpress.yupay.Models.Cloth
import com.shibuyaxpress.yupay.R

class ClothAdapter(context: Context) : RecyclerView.Adapter<ClothHolder>() {
    private var mInflater:LayoutInflater?=null
    private var mCloth:List<Cloth>?=null

    init {
        mInflater= LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothHolder {
        var itemView: View =mInflater!!.inflate(R.layout.recyclerview_item,parent,false)
        return ClothHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClothHolder, position: Int) {
        if(mCloth!=null){
            var current: Cloth =mCloth!!.get(position)
            holder.nameCloth!!.text=current.name
        }else{
            holder.nameCloth!!.text="No tiene ropa disponible en su inventario!!"
        }
    }

    override fun getItemCount(): Int {
        return if (mCloth!=null){
            mCloth!!.size
        }else{
            0
        }
    }

    fun setNameOfCloth(cloth:List<Cloth>){
        mCloth=cloth
        notifyDataSetChanged()
    }

}