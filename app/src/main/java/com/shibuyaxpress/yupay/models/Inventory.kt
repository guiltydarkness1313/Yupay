package com.shibuyaxpress.yupay.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.support.annotation.NonNull
import com.shibuyaxpress.yupay.utils.TypoConverter
import java.util.*

@Entity(tableName = "inventory")
data class Inventory (
        @PrimaryKey(autoGenerate = true) var id: Int?,
        @NonNull var stock:Int?,
        @NonNull var lastUpdate:Date = Date(System.currentTimeMillis())

)