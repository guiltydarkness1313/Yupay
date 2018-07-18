package com.shibuyaxpress.yupay.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shibuyaxpress.yupay.GlideApp
import com.shibuyaxpress.yupay.holders.ItemHolder
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.R

class ItemAdapter(context:Context):RecyclerView.Adapter<ItemHolder>() {
    private var mInflater:LayoutInflater? = null
    private var mItem:List<Cloth>? = null

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView: View = mInflater!!.inflate(R.layout.cloth_item,parent,false)
        return ItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if(mItem != null){
            mItem!!.size
        }else{
            16
        }
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        //if (mItem != null){
            //val current: Cloth = mItem!![position]
            holder.name!!.text = "hola"
            GlideApp.with(mInflater!!.context)
                    .load("/storage/emulated/0/yupay/1531649517050.jpg")
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .fitCenter()
                    .into(holder.image!!)
        //}
    }
}