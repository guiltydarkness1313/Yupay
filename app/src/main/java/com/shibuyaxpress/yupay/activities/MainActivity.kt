   package com.shibuyaxpress.yupay.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.shibuyaxpress.yupay.adapters.ClothAdapter
import com.shibuyaxpress.yupay.repository.ClothViewModel
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.R
import com.shibuyaxpress.yupay.models.Inventory
import com.shibuyaxpress.yupay.repository.ClothRoomDatabase
import com.shibuyaxpress.yupay.utils.SpacesItemDecoration
import java.util.*


   class MainActivity : AppCompatActivity() {

       private var mClothViewModel: ClothViewModel?=null
       private var recyclerView: RecyclerView? = null
       private var buttonFAB: FloatingActionButton? = null
       private val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           setContentView(R.layout.activity_main)
//           supportActionBar!!.title = "Mis productos"
           buttonFAB = findViewById(R.id.fabAdd)
           recyclerView=findViewById(R.id.recyclerview)
           var adapter = ClothAdapter(this)
           recyclerView!!.adapter=adapter
           val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
           recyclerView!!.addItemDecoration(SpacesItemDecoration(spacingInPixels))
           val conf = this.resources.configuration
           checkOrientation(conf)
           mClothViewModel=ViewModelProviders.of(this).get(ClothViewModel::class.java)
           mClothViewModel!!.getAllClothes().observe(this, Observer{ cloth->
               adapter.setNameOfCloth(cloth!!)
           })
           buttonFAB!!.setOnClickListener {
               val intent=Intent(this@MainActivity, NewClothActivity::class.java)
               startActivityForResult(intent,NEW_WORD_ACTIVITY_REQUEST_CODE)
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

       @RequiresApi(Build.VERSION_CODES.O)
       override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           super.onActivityResult(requestCode, resultCode, data)
           if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
               if (data != null) {
                   val appDatabase = ClothRoomDatabase.getDatabase(this)
                   val inventory = Inventory(null, data.getIntExtra("stock", 0))
                   //se puede hacer insert sin la necesidad de un Async !!
                   Log.d("CAGADAS",appDatabase!!.inventoryDAO().getAllInventory().toString())
                   val idInventory :Long = appDatabase!!.inventoryDAO().insert(inventory)
                   val currentInventory = appDatabase!!.inventoryDAO().getItemBySearch(idInventory)
                   Log.d("valor",currentInventory.toString())
                   val cloth = Cloth(null, data.getStringExtra("name"),
                           data.getDoubleExtra("price", 0.0),
                           data.getStringExtra("image"), currentInventory!![0].id!!.toInt(), Date())
                   Log.d("Datos", cloth.toString())
                   appDatabase!!.clothDao().insert(cloth)
               } else {
                   Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
               }
           }else{
               Toast.makeText(applicationContext,"los datos estan vacios", Toast.LENGTH_SHORT).show()
           }
       }

   }
