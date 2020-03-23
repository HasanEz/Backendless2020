package com.example.backendless2020.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.example.backendless2020.R
import com.example.backendless2020.models.OrderDetails
import com.example.backendless2020.models.Orders

class OrdersAdapter(private val context: Context, private val ordersList: List<Orders>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.item_order,null)

        //pointers to view components

        val orderNumberTv = view.findViewById<TextView>(R.id.orderNumTV)
        val orderDetailsBtn  = view.findViewById<TextView>(R.id.btnDetails)

        val user= Backendless.UserService.CurrentUser()

        //setting order details and button on click
        orderNumberTv.text = ordersList[position].orderNumber.replace(user.userId,"")

        orderDetailsBtn.setOnClickListener {

            // fetching order details with same number as order


            val dataQueryBuilder = DataQueryBuilder.create()
            val whereClause = "orderNumber = '${ordersList[position].orderNumber}'"

            dataQueryBuilder.whereClause = whereClause

            Backendless.Data.of(OrderDetails::class.java).find(dataQueryBuilder,object: AsyncCallback<List<OrderDetails>>{
                override fun handleFault(fault: BackendlessFault?) {

                }

                override fun handleResponse(response: List<OrderDetails>?) {

                    Toast.makeText(context,"${response?.get(0)?.product}",Toast.LENGTH_LONG).show()





                }


            })
        }



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