package com.shibuyaxpress.yupay.repository

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.models.Inventory

class ClothViewModel(application: Application) : AndroidViewModel(application) {
    private var mRepository: RoomRepository?=null
    private var mAllClothes:LiveData<List<Cloth>>?=null

    fun getAllClothes():LiveData<List<Cloth>>{
        return mAllClothes!!
    }
    fun insert(cloth: Cloth){
        mRepository!!.insert(cloth)
    }
    fun searchItemFromInventory(item:Long): List<Inventory> {
        return mRepository!!.queryAnInventoryItem(item)
    }
    fun getItems():List<Inventory>?{
     return mRepository!!.getItems()
    }
    fun setInventory(item:Inventory):Long{
        return mRepository!!.insertInventory(item)
    }

    init {
        mRepository= RoomRepository(application)
        mAllClothes= mRepository!!.getAllClothes()
    }
}