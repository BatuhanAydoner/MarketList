package com.moonturns.marketlist.view

import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Update
import com.moonturns.marketlist.R
import com.moonturns.marketlist.database.DatabaseContract
import com.moonturns.marketlist.database.DatabaseRepository
import com.moonturns.marketlist.model.MarketList
import kotlinx.android.synthetic.main.fragment_new_product.*
import kotlinx.android.synthetic.main.rv_item.*

class NewProductFragment : Fragment() {

    // Product id from navigation argument
    private var marketList: MarketList? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_new_product, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonClickEvents()

        stateOfNewProduct()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    // Button click events
    private fun buttonClickEvents() {
        btnSave.setOnClickListener {
            addMarketListToRoom()
        }

        btnDeleteItem.setOnClickListener {
            deleteFromRoom()
            it.visibility = View.GONE
        }

        btnUpdateItem.setOnClickListener {
            updateMarketList()
        }
    }

    // Add a new product to room
    private fun addMarketListToRoom() {
        var newProduct = MarketList( 0, etProductName.text.toString(), etProductCount.text.toString(), 0)
        context?.let {
            DatabaseRepository.InsertItem(it).execute(newProduct)
        }
    }

    // Delete a product from room
    private fun deleteFromRoom() {
        context?.let {
            DatabaseRepository.DeleteItem(it).execute(marketList?.listId)
            etProductName.text.clear()
            etProductCount.text.clear()
        }

    }

    // Update a product from room
    private fun updateMarketList() {
        var contentValues = ContentValues()
        contentValues.put(DatabaseContract.MarketListContract.COLUMN_LIST_ID, marketList?.listId)
        contentValues.put(DatabaseContract.MarketListContract.COLUMN_NAME, etProductName.text.toString())
        contentValues.put(DatabaseContract.MarketListContract.COLUMN_COUNT, etProductCount.text.toString())
        context?.let {
            DatabaseRepository.UpdateItem(it).execute(contentValues)
            Log.e("myApp", marketList?.name + " " + marketList?.count)
        }
    }

    // Check what product is saved
    private fun stateOfNewProduct() {
        arguments?.let {
            marketList = NewProductFragmentArgs.fromBundle(it).product
        }

        if (marketList?.name != "") {
            etProductName.text = Editable.Factory.getInstance().newEditable(marketList?.name)
            etProductCount.text = Editable.Factory.getInstance().newEditable(marketList?.count)
            btnDeleteItem.visibility = View.VISIBLE
            btnUpdateItem.visibility = View.VISIBLE
        }
    }
}