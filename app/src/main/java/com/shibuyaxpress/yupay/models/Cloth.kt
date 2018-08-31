package com.shibuyaxpress.yupay.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "cloth",
        foreignKeys = arrayOf(ForeignKey(entity = Inventory::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("inventoryId"),
        onDelete =  ForeignKey.CASCADE)),
        indices = arrayOf(Index(value = "inventoryId")))
data class Cloth (@PrimaryKey(autoGenerate = true) var id: Long? = null,
                  @NonNull var name: String? = null,
                  @NonNull var price: Double? = null,
                  @NonNull var image: String? = null,
                  @NonNull var inventoryId:Int? = null,
                  var createdAt:Date? = null
                  )

