package com.shibuyaxpress.yupay.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.shibuyaxpress.yupay.R

class ClothHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var name:TextView? = null
    var image:ImageView? = null
    init {
        name = itemView.findViewById(R.id.textVIewCloth)
        image = itemView.findViewById(R.id.imageViewCloth)
    }
}