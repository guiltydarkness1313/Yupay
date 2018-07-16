   package com.shibuyaxpress.yupay.Activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.shibuyaxpress.yupay.Adapters.ClothAdapter
import com.shibuyaxpress.yupay.Repository.ClothViewModel
import com.shibuyaxpress.yupay.Models.Cloth
import com.shibuyaxpress.yupay.R
import kotlinx.android.synthetic.main.activity_main.*


   class MainActivity : AppCompatActivity() {
    private var mClothViewModel: ClothViewModel?=null

       val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var buttonFAB:FloatingActionButton=findViewById(R.id.fabAdd)
        var recyclerview:RecyclerView=findViewById(R.id.recyclerview)
        var adapter: ClothAdapter = ClothAdapter(this)
        recyclerview.adapter=adapter
        recyclerview.layoutManager=LinearLayoutManager(this)
        mClothViewModel=ViewModelProviders.of(this).get(ClothViewModel::class.java)
        mClothViewModel!!.getAllClothes().observe(this, Observer{ cloth->
            adapter.setNameOfCloth(cloth!!)
            }
        )
        buttonFAB.setOnClickListener {
            val intent=Intent(this@MainActivity, NewClothActivity::class.java)
            startActivityForResult(intent,NEW_WORD_ACTIVITY_REQUEST_CODE);
        }
        fabAdd2.setOnClickListener {
            val intent = Intent(this@MainActivity, ItemActivity::class.java)
            startActivity(intent)
        }
    }
       override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           super.onActivityResult(requestCode, resultCode, data)
           if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
               val cloth= Cloth(null, data!!.getStringExtra(NewClothActivity().EXTRA_REPLY), 25.0, null)
               //cloth.name=(data!!.getStringExtra(NewClothActivity::EXTRA_REPLY.toString()))
               Log.d("Datos",cloth.toString())
               mClothViewModel!!.insert(cloth)
           }else{
               Toast.makeText(applicationContext, R.string.empty_not_saved,Toast.LENGTH_LONG).show()
           }
       }
   }
