package com.shibuyaxpress.yupay.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.tasks.InsertAsyncTask

class ClothRepository(application: Application) {

    private var clothDao: ClothDAO?=null
    private var allClothes:LiveData<List<Cloth>>?=null

    init {
        val db: ClothRoomDatabase?= ClothRoomDatabase.getDatabase(application)
        clothDao=db!!.clothDao()
        allClothes= clothDao!!.getAllClothes()
    }

    fun getAllClothes(): LiveData<List<Cloth>>? {
        return allClothes
    }

    fun insert(cloth: Cloth){
        InsertAsyncTask(clothDao).execute(cloth)
    }



}