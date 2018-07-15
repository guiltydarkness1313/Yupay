package com.shibuyaxpress.yupay.Holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.shibuyaxpress.yupay.R

class ClothHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameCloth:TextView?=null

    init {
        nameCloth=itemView.findViewById(R.id.textView)
    }
}