package com.moonturns.marketlist.database

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.moonturns.marketlist.model.MarketList
import java.lang.IllegalArgumentException
import java.util.regex.Matcher

class MarketListContentProvider : ContentProvider() {

    private lateinit var listDao: ListDao

    companion object {
        val CONTENT_AUTHORITY = "com.moonturns.marketlist"
        val PATH_MARKET_LIST = "list"

        val BASE_URI = Uri.parse("content://${CONTENT_AUTHORITY}")
        val CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH_MARKET_LIST)
    }

    private val matcher: UriMatcher

    init {
        matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(CONTENT_AUTHORITY, PATH_MARKET_LIST, 100)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        var _uri: Uri? = null
        when (matcher.match(uri)) {
            100 -> {
                values?.let {
                    var inserted = listDao.insert(MarketList.fromContentValues(values))
                    if (inserted > 0) {
                        context?.contentResolver?.notifyChange(uri, null)
                        _uri = ContentUris.withAppendedId(CONTENT_URI, inserted)
                    }
                }
            }
            else -> {
                throw IllegalArgumentException()
            }
        }

        return _uri
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var cursor: Cursor? = null
        when (matcher.match(uri)) {
            100 -> {
                cursor = listDao.getAll()
                if (context != null) {
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> {
                throw IllegalArgumentException()
            }
        }

        return cursor
    }

    override fun onCreate(): Boolean {
        listDao = ApplicationDatabase(context!!).listDao()
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        var updated = 0
        when (matcher.match(uri)) {
            100 -> {
                values?.let {
                    updated = listDao.update(MarketList.fromContentValues(values))
                }
            }
        }

        return updated
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        var deleted = 0
        when (matcher.match(uri)) {
            100 -> {
                deleted = listDao.delete(ContentUris.parseId(uri).toInt())
            }
        }

        return deleted
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }
}