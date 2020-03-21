package com.example.backendless2020.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.backendless2020.R
import com.example.backendless2020.models.Orders

class OrdersAdapter(private val context: Context, private val ordersList: List<Orders>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.item_order,null)

        //pointers to view components

        val orderNumberTv = view.findViewById<TextView>(R.id.orderNumTV)

        orderNumberTv.text = ordersList[position].orderNumber.toString()

        return view


    }

    override fun getItem(position: Int): Any {

        return ordersList[position]
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()
    }

    override fun getCount(): Int {

        return ordersList.size
    }


}