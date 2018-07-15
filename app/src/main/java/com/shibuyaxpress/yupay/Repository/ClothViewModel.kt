package com.shibuyaxpress.yupay.Repository

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.shibuyaxpress.yupay.Models.Cloth
import com.shibuyaxpress.yupay.Repository.ClothRepository

class ClothViewModel(application: Application) : AndroidViewModel(application) {
    private var mRepository: ClothRepository?=null
    private var mAllClothes:LiveData<List<Cloth>>?=null

    fun getAllClothes():LiveData<List<Cloth>>{
        return mAllClothes!!
    }
    fun insert(cloth: Cloth){
        mRepository!!.insert(cloth)
    }

    init {
        mRepository= ClothRepository(application)
        mAllClothes= mRepository!!.getAllClothes()
    }
}