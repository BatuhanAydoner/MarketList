package com.moonturns.marketlist.model

import android.content.ContentValues
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moonturns.marketlist.database.DatabaseContract

// Model class
@Entity(tableName = DatabaseContract.MarketListContract.TABLE_NAME)
data class MarketList(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseContract.MarketListContract.COLUMN_LIST_ID)
    var listId: Int,
    @ColumnInfo(name = DatabaseContract.MarketListContract.COLUMN_NAME)
    var name: String,
    @ColumnInfo(name = DatabaseContract.MarketListContract.COLUMN_COUNT)
    var count: String,
    @ColumnInfo(name = DatabaseContract.MarketListContract.COLUMN_DONE)
    var done: Int
) {
    companion object {
        fun fromContentValues(contentValues: ContentValues): MarketList {
            var id = 0
            var _name = ""
            var _count = ""
            var _done = 0

            if (contentValues.containsKey(DatabaseContract.MarketListContract.COLUMN_LIST_ID))
                id = contentValues.getAsInteger(DatabaseContract.MarketListContract.COLUMN_LIST_ID)

            if (contentValues.containsKey(DatabaseContract.MarketListContract.COLUMN_NAME))
                _name = contentValues.getAsString(DatabaseContract.MarketListContract.COLUMN_NAME)

            if (contentValues.containsKey(DatabaseContract.MarketListContract.COLUMN_COUNT))
                _count = contentValues.getAsString(DatabaseContract.MarketListContract.COLUMN_COUNT)

            if (contentValues.containsKey(DatabaseContract.MarketListContract.COLUMN_DONE))
                _done = contentValues.getAsInteger(DatabaseContract.MarketListContract.COLUMN_DONE)

            return MarketList(id, _name, _count, _done)
        }
    }
}