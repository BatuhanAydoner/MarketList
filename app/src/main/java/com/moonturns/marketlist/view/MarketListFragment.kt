package com.moonturns.marketlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.moonturns.marketlist.R
import com.moonturns.marketlist.database.DatabaseRepository
import kotlinx.android.synthetic.main.fragment_market_list.*

class MarketListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_market_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.let {
            DatabaseRepository.ShowList(it).execute()
        }
        buttonClickEvents()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    // Click events of buttons
    private fun buttonClickEvents() {
        fabNewProduct.setOnClickListener {
            var action = MarketListFragmentDirections.actionMarketListFragmentToNewProductFragment()
            view?.findNavController()?.navigate(action)
        }
    }
}