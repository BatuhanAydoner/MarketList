package com.moonturns.marketlist.database

import android.content.ContentValues
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.moonturns.marketlist.model.MarketList
import java.lang.ref.WeakReference

class DatabaseRepository {
    class InsertItem(context: Context) : AsyncTask<MarketList, Any, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        private var weakReference = WeakReference(context)

        override fun doInBackground(vararg params: MarketList?): String {
            var contentValues = ContentValues()
            contentValues.put(DatabaseContract.MarketListContract.COLUMN_LIST_ID, params[0]?.listId)
            contentValues.put(DatabaseContract.MarketListContract.COLUMN_NAME, params[0]?.name)
            contentValues.put(DatabaseContract.MarketListContract.COLUMN_COUNT, params[0]?.count)
            contentValues.put(DatabaseContract.MarketListContract.COLUMN_DONE, params[0]?.done)
            var uri = weakReference.get()?.contentResolver?.insert(
                MarketListContentProvider.CONTENT_URI,
                contentValues
            )
            if (uri != null)
                return "Inserted"
            else
                return "Could not inserted"
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Toast.makeText(weakReference.get(), result, Toast.LENGTH_SHORT).show()
        }
    }

    class ShowList(context: Context) : AsyncTask<Any, Any, List<MarketList>>() {

        private var weakReference = WeakReference(context)

        override fun doInBackground(vararg params: Any?): List<MarketList>? {
            var cursor = weakReference.get()?.contentResolver?.query(
                MarketListContentProvider.CONTENT_URI,
                null,
                null,
                null,
                null,
                null
            )

            var list = ArrayList<MarketList>()

            cursor?.let {
                while (cursor.moveToNext()) {
                    list.add(
                        MarketList(
                            cursor.getInt(cursor.getColumnIndex(DatabaseContract.MarketListContract.COLUMN_LIST_ID)),
                            cursor.getString(cursor.getColumnIndex(DatabaseContract.MarketListContract.COLUMN_NAME)),
                            cursor.getString(cursor.getColumnIndex(DatabaseContract.MarketListContract.COLUMN_COUNT)),
                            cursor.getInt(cursor.getColumnIndex(DatabaseContract.MarketListContract.COLUMN_DONE))
                        )
                    )
                }
            }

            return list
        }

        override fun onPostExecute(result: List<MarketList>?) {
            super.onPostExecute(result)
            
        }
    }
}