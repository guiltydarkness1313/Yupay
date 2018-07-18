package com.shibuyaxpress.yupay.tasks

import android.os.AsyncTask
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.repository.ClothDAO

class InsertAsyncTask(dao: ClothDAO?) : AsyncTask<Cloth, Void, Void>() {

    private var mAsyncTaskDao: ClothDAO?= dao

    override fun doInBackground(vararg params: Cloth?): Void? {
        mAsyncTaskDao?.insert(params[0])
        return null
    }

}