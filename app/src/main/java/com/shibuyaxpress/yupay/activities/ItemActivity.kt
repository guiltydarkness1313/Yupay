package com.shibuyaxpress.yupay.activities

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.shibuyaxpress.yupay.adapters.ItemAdapter
import com.shibuyaxpress.yupay.R

class ItemActivity : AppCompatActivity() {

    var recycler:RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        recycler = findViewById(R.id.recycler_item)
        var adapter = ItemAdapter(this)
        recycler!!.adapter = adapter
        val conf = this.resources.configuration
        checkOrientation(conf)

    }

    private fun checkOrientation(conf:Configuration){
        if(conf.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recycler!!.layoutManager = GridLayoutManager(this,4)
        }
        else if(conf.orientation == Configuration.ORIENTATION_PORTRAIT){
            recycler!!.layoutManager = GridLayoutManager(this,2)
        }
    }
}
