package com.moonturns.marketlist.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moonturns.marketlist.model.MarketList

@Database(entities = arrayOf(MarketList::class), version = 1)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun listDao(): Dao

    companion object {
        @Volatile
        private var instance: ApplicationDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
            instance ?: createInstanceOfApplicationDatabase(context).also {
                instance = it
            }
        }

        private fun createInstanceOfApplicationDatabase(context: Context) = Room.databaseBuilder(context, ApplicationDatabase::class.java, "marketlist.db").build()
    }
}