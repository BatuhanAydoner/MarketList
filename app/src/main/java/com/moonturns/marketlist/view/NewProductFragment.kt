package com.moonturns.marketlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moonturns.marketlist.R
import com.moonturns.marketlist.database.DatabaseRepository
import com.moonturns.marketlist.model.MarketList
import kotlinx.android.synthetic.main.fragment_new_product.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewProductFragment : Fragment() {
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun buttonClickEvents() {
        btnSave.setOnClickListener {
            context?.let {
                var newProduct = MarketList( 0, "Water", "3L", 0)
                DatabaseRepository.InsertItem(it).execute(newProduct)
            }
        }
    }
}