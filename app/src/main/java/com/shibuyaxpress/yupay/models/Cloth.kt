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
data class Cloth (@PrimaryKey(autoGenerate = true) var id: Int?,
                  @NonNull var name: String?,
                  @NonNull var price: Double?,
                  @NonNull var image: String?,
                  @NonNull var inventoryId:Int?,
                  var createdAt:Date?
                  )

