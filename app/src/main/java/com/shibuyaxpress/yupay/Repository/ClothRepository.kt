package com.shibuyaxpress.yupay.Repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.shibuyaxpress.yupay.Models.Cloth
import com.shibuyaxpress.yupay.Tasks.insertAsyncTask

class ClothRepository(application: Application) {
    private var clothDao: ClothDAO?=null
    private var allClothes:LiveData<List<Cloth>>?=null

    fun getAllClothes(): LiveData<List<Cloth>>? {
        return allClothes
    }

    fun insert(cloth: Cloth){
        insertAsyncTask(clothDao).execute(cloth)
    }

    init {
        val db: ClothRoomDatabase?= ClothRoomDatabase.getDatabase(application)
        clothDao=db!!.clothDao()
        allClothes= clothDao!!.getAllClothes()
    }

}