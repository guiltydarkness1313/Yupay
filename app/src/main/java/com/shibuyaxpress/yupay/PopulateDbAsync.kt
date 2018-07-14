package com.shibuyaxpress.yupay

import android.os.AsyncTask

class PopulateDbAsync(db: ClothRoomDatabase) : AsyncTask<Void, Void, Void>() {
    private var mDao:ClothDAO?=null

    override fun doInBackground(vararg params: Void):Void? {
        mDao!!.deleteAll()
        var cloth=Cloth(null,"Polo",25.0,null)
        mDao!!.insert(cloth)
        cloth= Cloth(null,"Casaca",30.0,null)
        mDao!!.insert(cloth)
        return null
    }

    init {
        mDao=db.clothDao()
    }
}