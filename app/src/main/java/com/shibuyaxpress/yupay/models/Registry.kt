package com.shibuyaxpress.yupay.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.sql.Timestamp
import java.util.*

@Entity(tableName = "registry",
        foreignKeys = arrayOf(ForeignKey(entity = Cloth::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("clothId"),
        onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Ticket::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("ticketId"),
        onDelete = ForeignKey.CASCADE)),
        indices = arrayOf(Index(value = "clothId"),Index(value = "ticketId")))
data class Registry (
        @PrimaryKey(autoGenerate = true) var id:Int?,
        var ticketId:Int?,
        var clothId:Int?,
        var registryDate: Date? = Date(System.currentTimeMillis())
)