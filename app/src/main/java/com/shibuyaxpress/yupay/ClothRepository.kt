package com.shibuyaxpress.yupay

import android.app.Application
import android.arch.lifecycle.LiveData

class ClothRepository(application: Application) {
    private var clothDao:ClothDAO?=null
    private var allClothes:LiveData<List<Cloth>>?=null

    fun getAllClothes(): LiveData<List<Cloth>>? {
        return allClothes
    }

    fun insert(cloth:Cloth){
        insertAsyncTask(clothDao).execute(cloth)
    }

    init {
        val db:ClothRoomDatabase?=ClothRoomDatabase.getDatabase(application)
        clothDao=db!!.clothDao()
        allClothes= clothDao!!.getAllClothes()
    }

}