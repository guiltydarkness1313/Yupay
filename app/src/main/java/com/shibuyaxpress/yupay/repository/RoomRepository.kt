package com.shibuyaxpress.yupay.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.models.Inventory
import com.shibuyaxpress.yupay.tasks.InsertAsyncTask
import com.shibuyaxpress.yupay.tasks.InsertInventory

class RoomRepository(application: Application) {

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
    fun queryAnInventoryItem(item : Long) : List<Inventory>{
        return inventoryDao!!.getItemBySearch(item)
    }

    fun getAllClothes(): LiveData<List<Cloth>>? {
        return allClothes
    }

    fun insert(cloth: Cloth){
        InsertAsyncTask(clothDao).execute(cloth)
    }

    fun insertInventory(item: Inventory):Long{
        return InsertInventory(inventoryDao).execute(item).get()
    }



}