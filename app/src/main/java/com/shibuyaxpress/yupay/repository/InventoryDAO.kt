package com.shibuyaxpress.yupay.repository

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.shibuyaxpress.yupay.models.Inventory
@Dao
interface InventoryDAO {
    @Insert
    fun insert(item:Inventory?):Long

    @Query("DELETE FROM inventory")
    fun deleteAll()

    @Query("SELECT * FROM inventory ORDER BY id ASC")
    fun getAllInventory():List<Inventory>

    @Query("SELECT * FROM inventory WHERE id = :id")
    fun getItemBySearch(id:Long?):LiveData<List<Inventory>>
}