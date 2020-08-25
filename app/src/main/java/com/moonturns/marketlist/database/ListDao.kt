package com.moonturns.marketlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.moonturns.marketlist.model.MarketList

@Dao
interface ListDao {
    @Query("SELECT * FROM ${DatabaseContract.MarketListContract.TABLE_NAME}")
    fun getAll(): List<MarketList>

    @Insert
    fun insert(vararg marketList: MarketList): List<Long>

    @Update
    fun update(marketList: MarketList)

    @Query("DELETE FROM ${DatabaseContract.MarketListContract.TABLE_NAME} WHERE ${DatabaseContract.MarketListContract.COLUMN_LIST_ID} = :id")
    fun delete(id: Int)
}