package com.shibuyaxpress.yupay.repository

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.shibuyaxpress.yupay.models.Cloth

@Dao
interface ClothDAO {
    @Insert
    fun insert(cloth: Cloth?):Long

    @Query("DELETE FROM cloth")
    fun deleteAll()

    @Query("DELETE FROM cloth WHERE id = :id")
    fun deleteFromCloth(id:Long?)

    @Query("SELECT * FROM cloth ORDER BY name ASC")
    fun getAllClothes():LiveData<List<Cloth>>

    @Query("SELECT * FROM cloth WHERE id = :id")
    fun getClothById(id:Int):List<Cloth>
}