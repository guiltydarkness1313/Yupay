package com.shibuyaxpress.yupay.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import java.sql.Timestamp
import java.util.*

@Entity(tableName = "ticket")
data class Ticket(
        @PrimaryKey(autoGenerate = true) var id:Int?,
        var date:Date?,
        var totalMoney:Double?
)