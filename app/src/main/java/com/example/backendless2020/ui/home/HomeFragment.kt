package com.example.backendless2020.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.example.backendless2020.R
import com.example.backendless2020.adapters.ProductsAdapter
import com.example.backendless2020.adapters.test
import com.example.backendless2020.models.OrderDetails
import com.example.backendless2020.models.Orders
import com.example.backendless2020.models.Products
import com.example.backendless2020.ui.orders.OrdersFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {


        //Inflating

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        // Fragment text
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = "Products" })

        //Order Button On Click

        root.findViewById<Button>(R.id.btnOrder)?.setOnClickListener {

            // CURRENT USER
            val user = Backendless.UserService.CurrentUser()

            //CREATING ORDER INSTANCE
            var testOrder = Orders()
            var testOrdersList = mutableListOf<Orders>()

            // Order Client name
            testOrder.clientName = user.getProperty("name").toString()

            //getting orders object count (to id for order) <- MUST REPAIR

            Backendless.Data.of("Orders").getObjectCount(object : AsyncCallback<Int>{

                override fun handleFault(fault: BackendlessFault?) {}

                override fun handleResponse(response: Int?) {

                    //START SAVING >>>>>>>>

                    testOrder.orderNumber = response!! + 1
                    //Saving Order >>
                    testOrder.saveAsync(object : AsyncCallback<Orders?>{

                        //ERROR
                        override fun handleFault(fault: BackendlessFault?) { Toast.makeText(context,"NYKAT",Toast.LENGTH_LONG).show() }

                        override fun handleResponse(response: Orders?) {
                            // Order Saved...

                            testOrder = response!!
                            testOrdersList.add(response)

                            //ADDING RELATION : ORDER TO USER (1:N)

                            Backendless.Persistence.of(BackendlessUser::class.java).addRelation(user,"orders",testOrdersList,object : AsyncCallback<Int>{

                                override fun handleFault(fault: BackendlessFault?) {
                                    Log.e("Backenless error",fault?.message)
                                    Toast.makeText(context,"NYKAT",Toast.LENGTH_LONG).show()
                                }

                                override fun handleResponse(response: Int?) {

                                    Toast.makeText(context,"RELATION ORDER TO USER ADDED",Toast.LENGTH_LONG).show()}
                            })

                            // SAVING ORDER DETAILS LIST

                            var orderDetailsList = ProductsAdapter.orderDetailsList


                            //SORTING LIST >>>>
                            var iterator = orderDetailsList.iterator()

                            iterator.forEach {

                                it.orderNumber = testOrder.orderNumber
                                if(it.amount == 0 || it.amount == null){iterator.remove()}
                            }


                            Backendless.Data.of(OrderDetails::class.java).create( orderDetailsList,object : AsyncCallback<List<String>>{

                                override fun handleFault(fault: BackendlessFault?) {

                                    Log.d("Backenless error",fault?.message)
                                    Toast.makeText(context,"Error saving order details list ${fault?.message}",Toast.LENGTH_LONG).show()
                                }

                                override fun handleResponse(response: List<String>?) {
                                    Toast.makeText(context,"order details list saved",Toast.LENGTH_LONG).show()

                                    // NEED TO ADD RELATION TO ORDER
                                    //ORDER FINISHED MOVE TO ORDERS ACTIVITY/FRAG

                                    val navController = root.findNavController()
                                    navController.navigate(R.id.nav_orders)

                                }
                            })

                        }
                    })
                }
            })
        }

        return root
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)


        // Getting Products List

        Backendless.Data.of(Products::class.java).find(object: AsyncCallback<List<Products>>{
            override fun handleFault(fault: BackendlessFault?) {

                Toast.makeText(context,"retrieving table error : ${fault?.message} ",Toast.LENGTH_SHORT).show()
            }

            override fun handleResponse(response: List<Products>?) {


                //setting adapter + passing list size to companion object

                productsListSize = response!!.size

                producstList.adapter = ProductsAdapter(context,response!!)

                for(i in 0 until response.size){
                    var orderDetail = OrderDetails()
                    orderDetail.amount = 0

                    ProductsAdapter.orderDetailsList.add(i, OrderDetails())
                }
            }


        })
    }

    companion object HomeFragCompanion {

        var productsListSize = 0

    }
}









