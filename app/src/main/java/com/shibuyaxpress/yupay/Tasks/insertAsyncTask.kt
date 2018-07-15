package com.shibuyaxpress.yupay.Tasks

import android.os.AsyncTask
import com.shibuyaxpress.yupay.Models.Cloth
import com.shibuyaxpress.yupay.Repository.ClothDAO

class insertAsyncTask:AsyncTask<Cloth,Void,Void> {

    private var mAsyncTaskDao: ClothDAO?=null

    constructor(dao: ClothDAO?){
        mAsyncTaskDao=dao
    }

    override fun doInBackground(vararg params: Cloth?): Void? {
        mAsyncTaskDao?.insert(params[0])
        return null
    }

}