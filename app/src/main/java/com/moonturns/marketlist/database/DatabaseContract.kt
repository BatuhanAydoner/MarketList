package com.moonturns.marketlist.database

import android.provider.BaseColumns

class DatabaseContract {
    class MarketListContract(): BaseColumns {
        companion object {
            const val TABLE_NAME = "marketlist"

            const val COLUMN_LIST_ID = BaseColumns._ID
            const val COLUMN_NAME = "name"
            const val COLUMN_COUNT = BaseColumns._COUNT
            const val COLUMN_DONE = "DONE"
        }
    }
}