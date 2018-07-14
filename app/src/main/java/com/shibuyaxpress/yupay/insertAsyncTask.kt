package com.shibuyaxpress.yupay

import android.os.AsyncTask

class insertAsyncTask:AsyncTask<Cloth,Void,Void> {

    private var mAsyncTaskDao:ClothDAO?=null

    constructor(dao: ClothDAO?){
        mAsyncTaskDao=dao
    }

    override fun doInBackground(vararg params: Cloth?): Void? {
        mAsyncTaskDao?.insert(params[0])
        return null
    }

}