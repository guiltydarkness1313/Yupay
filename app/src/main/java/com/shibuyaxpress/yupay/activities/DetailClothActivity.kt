package com.shibuyaxpress.yupay.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.shibuyaxpress.yupay.R
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.utils.GlideApp
import kotlinx.android.synthetic.main.activity_detail_cloth.*
import java.text.SimpleDateFormat
import java.util.*

class DetailClothActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_cloth)
        val cloth = Cloth()
        cloth.name = intent.getStringExtra("name")
        //for string to date
        val dateStr = intent.getStringExtra("createdAt")
        val date = SimpleDateFormat("E MMM dd hh:mm:ss z yyyy").parse(dateStr)
        cloth.createdAt = date
        cloth.id = intent.getLongExtra("id",0)
        cloth.image = intent.getStringExtra("image")
        cloth.inventoryId = intent.getIntExtra("inventoryId",0)
        cloth.price = intent.getDoubleExtra("price",0.0)

        GlideApp.with(this)
                .load(cloth.image)
                .placeholder(R.drawable.ic_image_black_24dp)
                .fitCenter()
                .into(imgViewCloth)
        txtClothName.text = cloth.name
    }
}
