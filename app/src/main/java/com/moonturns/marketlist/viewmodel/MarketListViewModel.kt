package com.moonturns.marketlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moonturns.marketlist.model.MarketList

class MarketListViewModel: ViewModel() {
    var marketList = MutableLiveData<List<MarketList>>()
}