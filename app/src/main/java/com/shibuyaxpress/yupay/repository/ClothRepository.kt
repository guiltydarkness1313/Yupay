package com.shibuyaxpress.yupay.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.models.Inventory
import com.shibuyaxpress.yupay.tasks.InsertAsyncTask
import com.shibuyaxpress.yupay.tasks.InsertInventory

class ClothRepository(application: Application) {

    private var clothDao: ClothDAO?=null
    private var inventoryDao: InventoryDAO? = null
    private var allClothes:LiveData<List<Cloth>>?=null

    init {
        val db: ClothRoomDatabase?= ClothRoomDatabase.getDatabase(application)
        inventoryDao = db!!.inventoryDAO()
        clothDao=db!!.clothDao()
        allClothes= clothDao!!.getAllClothes()
    }
    fun getItems():List<Inventory>?{
        return inventoryDao!!.getAllInventory()
    }
    fun queryAnInventoryItem(item : Inventory) : LiveData<List<Inventory>>{
        return inventoryDao!!.getItemBySearch(item.id)
    }
    fun insertInventory(item : Inventory){
        InsertInventory(inventoryDao).execute(item)
    }

    fun getAllClothes(): LiveData<List<Cloth>>? {
        return allClothes
    }

    fun insert(cloth: Cloth){
        InsertAsyncTask(clothDao).execute(cloth)
    }



}