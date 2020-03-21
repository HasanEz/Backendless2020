package com.example.backendless2020.ui.orders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.example.backendless2020.R
import com.example.backendless2020.adapters.OrdersAdapter
import com.example.backendless2020.models.Orders

class OrdersFragment : Fragment() {

    private lateinit var ordersViewModel: OrdersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        ordersViewModel = ViewModelProviders.of(this).get(OrdersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_orders, container, false)
        val textView: TextView = root.findViewById(R.id.text_orders)
        ordersViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })





        //pointer to list view

        val ordersList = root.findViewById<ListView>(R.id.ordersLV)

        val currentUser = Backendless.UserService.CurrentUser()

        val whereClause = "clientName = '${currentUser.getProperty("name")}'"

        var dataQueryBuilder = DataQueryBuilder.create()
        dataQueryBuilder.whereClause = whereClause
        dataQueryBuilder.setPageSize(20)




        Backendless.Data.of(Orders::class.java).find(dataQueryBuilder,object:AsyncCallback<List<Orders>>{


            override fun handleFault(fault: BackendlessFault?) {

                Toast.makeText(context,"error loading orders ${fault?.message}",Toast.LENGTH_LONG).show()
                Log.d("--------------------",fault?.message)
            }

            override fun handleResponse(response: List<Orders>?) {

                ordersList.adapter = OrdersAdapter(context!!,response!!)

            }


        })




        return root
    }
}
