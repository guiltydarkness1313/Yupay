   package com.shibuyaxpress.yupay.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.shibuyaxpress.yupay.adapters.ClothAdapter
import com.shibuyaxpress.yupay.repository.ClothViewModel
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.R
import kotlinx.android.synthetic.main.activity_main.*


   class MainActivity : AppCompatActivity() {

       private var mClothViewModel: ClothViewModel?=null
       private var recyclerView: RecyclerView? = null
       private var buttonFAB: FloatingActionButton? = null
       private val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           setContentView(R.layout.activity_main)
           buttonFAB = findViewById(R.id.fabAdd)
           recyclerView=findViewById(R.id.recyclerview)
           var adapter = ClothAdapter(this)
           recyclerView!!.adapter=adapter
           val conf = this.resources.configuration
           checkOrientation(conf)
           mClothViewModel=ViewModelProviders.of(this).get(ClothViewModel::class.java)
           mClothViewModel!!.getAllClothes().observe(this, Observer{ cloth->
               adapter.setNameOfCloth(cloth!!)
           })
           buttonFAB!!.setOnClickListener {
               val intent=Intent(this@MainActivity, NewClothActivity::class.java)
               startActivityForResult(intent,NEW_WORD_ACTIVITY_REQUEST_CODE);
           }
       }

       private fun checkOrientation(conf: Configuration){
           if(conf.orientation == Configuration.ORIENTATION_LANDSCAPE){
               recyclerView!!.layoutManager = GridLayoutManager(this,4)
           }
           else if(conf.orientation == Configuration.ORIENTATION_PORTRAIT){
               recyclerView!!.layoutManager = GridLayoutManager(this,2)
           }
       }

       override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           super.onActivityResult(requestCode, resultCode, data)
           if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
               //cambiar codigo aqui
               val cloth= Cloth(null, data!!.getStringExtra("name"), data!!.getDoubleExtra("price",0.0), data!!.getStringExtra("image"),)
               //cloth.name=(data!!.getStringExtra(NewClothActivity::EXTRA_REPLY.toString()))
               Log.d("Datos",cloth.toString())
               mClothViewModel!!.insert(cloth)
           }else{
               Toast.makeText(applicationContext, R.string.empty_not_saved,Toast.LENGTH_LONG).show()
           }
       }

   }
