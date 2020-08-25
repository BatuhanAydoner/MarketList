package com.moonturns.marketlist.database

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.moonturns.marketlist.model.MarketList

@Dao
interface ListDao {
    @Query("SELECT * FROM ${DatabaseContract.MarketListContract.TABLE_NAME}")
    fun getAll(): Cursor

    @Insert
    fun insert(marketList: MarketList): Long

    @Update
    fun update(marketList: MarketList): Int

    @Query("DELETE FROM ${DatabaseContract.MarketListContract.TABLE_NAME} WHERE ${DatabaseContract.MarketListContract.COLUMN_LIST_ID} = :id")
    fun delete(id: Int): Int
}