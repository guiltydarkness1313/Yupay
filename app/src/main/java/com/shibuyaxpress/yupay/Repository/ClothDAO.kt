package com.shibuyaxpress.yupay.Repository

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.shibuyaxpress.yupay.Models.Cloth

@Dao
public interface ClothDAO {
    @Insert
    fun insert(cloth: Cloth?)

    @Query("DELETE FROM cloth")
    fun deleteAll()

    @Query("SELECT * FROM cloth ORDER BY name ASC")
    fun getAllClothes():LiveData<List<Cloth>>
}