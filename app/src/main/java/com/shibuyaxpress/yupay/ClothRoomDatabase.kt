package com.shibuyaxpress.yupay

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.support.annotation.NonNull

@Database(entities = arrayOf(Cloth::class), version = 1,exportSchema = false)
abstract class ClothRoomDatabase: RoomDatabase() {
    public abstract fun clothDao():ClothDAO
    companion object {
        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(@NonNull db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }
        private var INSTANCE:ClothRoomDatabase?=null

        fun getDatabase(context:Context):ClothRoomDatabase?{
            if (INSTANCE==null){
                synchronized(ClothRoomDatabase::class){
                    INSTANCE=Room.databaseBuilder(context.applicationContext,
                            ClothRoomDatabase::class.java,
                            "yupay")
                            //.addCallback(sRoomDatabaseCallback)
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE=null
        }
    }
}