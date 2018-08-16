package com.shibuyaxpress.yupay.tasks

import android.os.AsyncTask
import com.shibuyaxpress.yupay.repository.ClothDAO
import com.shibuyaxpress.yupay.repository.ClothRoomDatabase

class PopulateDbAsync(db: ClothRoomDatabase) : AsyncTask<Void, Void, Void>() {
    private var mDao: ClothDAO?=null

    override fun doInBackground(vararg params: Void):Void? {
        return null
    }

    init {
        mDao=db.clothDao()
    }
}