package com.shibuyaxpress.yupay.Holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.shibuyaxpress.yupay.R
import kotlinx.android.synthetic.main.cloth_item.view.*

class ItemHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    var name: TextView? = null
    var image: ImageView? = null
    init {
        name = itemView.findViewById(R.id.textVIewCloth)
        image = itemView.findViewById(R.id.imageViewCloth)
    }
}