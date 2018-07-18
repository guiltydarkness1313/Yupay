package com.shibuyaxpress.yupay.repository

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.*
import android.content.Context
import android.support.annotation.NonNull
import com.shibuyaxpress.yupay.models.Cloth
import com.shibuyaxpress.yupay.models.Inventory
import com.shibuyaxpress.yupay.models.Registry
import com.shibuyaxpress.yupay.models.Ticket
import com.shibuyaxpress.yupay.tasks.PopulateDbAsync
import com.shibuyaxpress.yupay.utils.TypoConverter

@Database(entities = arrayOf(Cloth::class,
        Inventory::class,
        Registry::class,
        Ticket::class),
        version = 2,
        exportSchema = false)
@TypeConverters(TypoConverter::class)
abstract class ClothRoomDatabase: RoomDatabase() {
    abstract fun clothDao(): ClothDAO
    abstract fun inventoryDAO():InventoryDAO
    companion object {
       /* private val migration1To2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE cloth ALTER COLUMN image")
            }

        }*/
        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(@NonNull db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }
        private var INSTANCE: ClothRoomDatabase?=null
        fun getDatabase(context:Context): ClothRoomDatabase?{
            if (INSTANCE ==null){
                synchronized(ClothRoomDatabase::class){
                    INSTANCE =Room.databaseBuilder(context.applicationContext,
                            ClothRoomDatabase::class.java,
                            "yupay")
                            //.addCallback(sRoomDatabaseCallback)
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE =null
        }
    }
}