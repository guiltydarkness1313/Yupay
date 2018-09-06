package com.shibuyaxpress.yupay.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import com.shibuyaxpress.yupay.R
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.utils.GlideApp
import kotlinx.android.synthetic.main.activity_detail_cloth.*
import java.text.SimpleDateFormat
import java.util.*

class
DetailClothActivity : AppCompatActivity() {
    private var scaleGestureDetector:ScaleGestureDetector? = null
    private var mScaleFactor:Float = 1.0f
    private var imageView:ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_cloth)
        imageView= findViewById(R.id.imgViewCloth)
        scaleGestureDetector = ScaleGestureDetector(this,ScaleListener())
        Log.d("Scale Size:",mScaleFactor.toString())
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
                .into(imageView!!)
        txtClothName.text = cloth.name
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector!!.onTouchEvent(event)
        return true
    }
    private inner class ScaleListener: ScaleGestureDetector.SimpleOnScaleGestureListener() {
        val originX = imageView!!.scaleX
        val originY = imageView!!.scaleY
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            mScaleFactor *= detector!!.scaleFactor
            mScaleFactor = Math.max(0.1f,Math.min(mScaleFactor,10.0f))
            imageView!!.scaleX = mScaleFactor
            imageView!!.scaleY = mScaleFactor
            print(mScaleFactor)
            if (mScaleFactor<=originX || mScaleFactor == originX*4 ){
                imageView!!.scaleX = originX
                imageView!!.scaleY = originY
            }
            return true
        }
    }

}
