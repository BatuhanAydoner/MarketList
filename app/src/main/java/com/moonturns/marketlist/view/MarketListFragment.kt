package com.moonturns.marketlist.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.moonturns.marketlist.R
import com.moonturns.marketlist.adapter.MarketListRecyclerviewAdapter
import com.moonturns.marketlist.database.DatabaseRepository
import com.moonturns.marketlist.viewmodel.MarketListViewModel
import kotlinx.android.synthetic.main.fragment_market_list.*

class MarketListFragment : Fragment() {

    private var myAdapter = MarketListRecyclerviewAdapter(arrayListOf())
    private lateinit var viewModel: MarketListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_market_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonClickEvents()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerview()
        viewModel = ViewModelProvider(this).get(MarketListViewModel::class.java)
        observeData()
        getData()
    }

    // Click events of buttons
    private fun buttonClickEvents() {
        fabNewProduct.setOnClickListener {
            var action = MarketListFragmentDirections.actionMarketListFragmentToNewProductFragment()
            view?.findNavController()?.navigate(action)
        }
    }

    // Init recyclerview
    private fun initRecyclerview() {
        rvMarketList.apply {
            adapter = myAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    // Observe live data
    private fun observeData() {
        viewModel.marketList.observe(viewLifecycleOwner, Observer {
            it?.let {
                myAdapter.update(it)
            }
        })
    }

    private fun getData() {
        context?.let {
            DatabaseRepository.ShowList(it, viewModel).execute()
        }
    }
}