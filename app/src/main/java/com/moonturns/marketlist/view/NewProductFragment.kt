package com.moonturns.marketlist.view

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moonturns.marketlist.R
import com.moonturns.marketlist.database.DatabaseRepository
import com.moonturns.marketlist.model.MarketList
import kotlinx.android.synthetic.main.fragment_new_product.*

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

        arguments?.let {
            marketList = NewProductFragmentArgs.fromBundle(it).product
        }

        if (marketList?.name != "") {
            etProductName.text = Editable.Factory.getInstance().newEditable(marketList?.name)
            etProductCount.text = Editable.Factory.getInstance().newEditable(marketList?.count)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun buttonClickEvents() {
        btnSave.setOnClickListener {
            context?.let {
                var newProduct = MarketList( 0, "Water", "3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L3L", 0)
                DatabaseRepository.InsertItem(it).execute(newProduct)
            }
        }
    }
}