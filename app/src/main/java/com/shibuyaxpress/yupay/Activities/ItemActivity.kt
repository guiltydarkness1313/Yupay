package com.shibuyaxpress.yupay.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.shibuyaxpress.yupay.Adapters.ItemAdapter
import com.shibuyaxpress.yupay.R

class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        val recycler:RecyclerView = findViewById(R.id.recycler_item)
        val adapter = ItemAdapter(this)
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(this,2)

    }
}
