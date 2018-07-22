package com.shibuyaxpress.yupay.tasks

import android.os.AsyncTask
import com.shibuyaxpress.yupay.models.Inventory
import com.shibuyaxpress.yupay.repository.InventoryDAO

class InsertInventory(dao : InventoryDAO?) : AsyncTask<Inventory, Void, Void>() {
    private var mAsyncTaskDao : InventoryDAO? = dao

    override fun doInBackground(vararg p0: Inventory?): Void? {
        mAsyncTaskDao?.insert(p0[0])
        return null
    }

}