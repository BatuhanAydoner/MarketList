package com.moonturns.marketlist.adapter

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.moonturns.marketlist.R
import com.moonturns.marketlist.database.DatabaseContract
import com.moonturns.marketlist.database.DatabaseRepository
import com.moonturns.marketlist.model.MarketList
import com.moonturns.marketlist.view.MarketListFragment
import com.moonturns.marketlist.view.MarketListFragmentDirections
import kotlinx.android.synthetic.main.rv_item.view.*

class MarketListRecyclerviewAdapter(var list: ArrayList<MarketList>) :
    RecyclerView.Adapter<MarketListRecyclerviewAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.txtId.text = "" + list[position].listId
        holder.itemView.txtProductName.text = list[position].name
        holder.itemView.txtCount.text = list[position].count
        holder.itemView.cbDone.text = list[position].name

        if (list[position].done == 1) {
            holder.itemView.cbDone.isChecked = true
        }

        holder.itemView.setOnClickListener {
            var action = MarketListFragmentDirections.actionMarketListFragmentToNewProductFragment(list[position])
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.cbDone.setOnCheckedChangeListener { buttonView, isChecked ->
            updateRoom(holder, position, isChecked)
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    // Update a row at room
    private fun updateRoom(holder: MyHolder, position: Int, isChecked: Boolean) {
        var checked = 0
        if (isChecked == true) {
            checked = 1
        }else {
            checked = 0
        }

        var contentValues = ContentValues()
        contentValues.put(DatabaseContract.MarketListContract.COLUMN_LIST_ID, list[position].listId)
        contentValues.put(DatabaseContract.MarketListContract.COLUMN_NAME, list[position].name)
        contentValues.put(DatabaseContract.MarketListContract.COLUMN_COUNT, list[position].count)
        contentValues.put(DatabaseContract.MarketListContract.COLUMN_DONE, checked)

        DatabaseRepository.UpdateItem(holder.itemView.context).execute(contentValues)
    }

    // Update adapter
    fun update(newList: List<MarketList>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}