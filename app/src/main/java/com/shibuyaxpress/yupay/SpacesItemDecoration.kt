package com.shibuyaxpress.yupay

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpacesItemDecoration (spacing:Int?): RecyclerView.ItemDecoration() {
    var spacing:Int? = null
    init {
        this.spacing = spacing
    }
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent!!.getChildAdapterPosition(view)
        val spanCount = 2
        if (position >= 0){
            val column = position % spanCount
            outRect?.left = spacing!! - column * spacing!! / spanCount
            outRect?.right = (column + 1) * spacing!! / spanCount
            if (position < spanCount){
                outRect?.top = spacing
            }
            outRect?.bottom = spacing
        }else{
            outRect?.left = 0
            outRect?.right = 0
            outRect?.top = 0
            outRect?.bottom = 0
        }
    }
}