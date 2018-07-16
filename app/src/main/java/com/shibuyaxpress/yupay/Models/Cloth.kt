package com.shibuyaxpress.yupay.Models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "cloth")
data class Cloth (@PrimaryKey(autoGenerate = true) var id: Int?,
                  @NonNull var name: String?,
                  @NonNull var price: Double?,
                  @NonNull var image: String?)

