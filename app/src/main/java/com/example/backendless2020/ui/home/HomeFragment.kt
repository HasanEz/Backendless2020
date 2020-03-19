package com.example.backendless2020.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.servercode.annotation.Async
import com.example.backendless2020.R
import com.example.backendless2020.adapters.productsAdapter
import com.example.backendless2020.models.DemoTable
import com.example.backendless2020.models.OrderDetails
import com.example.backendless2020.models.Orders
import com.example.backendless2020.models.Products
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

        //Order Button

        root.findViewById<Button>(R.id.btnOrder)?.setOnClickListener {

            // CURRENT USER

            val user = Backendless.UserService.CurrentUser()


            var testOrder = Orders()
            var testOrdersList = mutableListOf<Orders>()




            // Order Client name

            testOrder.clientName = user.getProperty("name").toString()

            // Order Detail



            //saving order

            testOrder.saveAsync(object : AsyncCallback<Orders?>{

                override fun handleFault(fault: BackendlessFault?) {
                    //ERROR
                    Toast.makeText(context,"NYKAT",Toast.LENGTH_LONG).show()

                }

                override fun handleResponse(response: Orders?) {

                    testOrder = response!!

                    testOrdersList.add(response)

                    //ADDING RELATION : ORDER TO USER (1:N)

                    Backendless.Persistence.of(BackendlessUser::class.java).addRelation(user,"orders",testOrdersList,object : AsyncCallback<Int>{

                        override fun handleFault(fault: BackendlessFault?) {Toast.makeText(context,"NYKAT",Toast.LENGTH_LONG).show() }

                        override fun handleResponse(response: Int?) {Toast.makeText(context,"RELATION ORDER TO USER ADDED",Toast.LENGTH_LONG).show()}


                    })

                    // SAVING ORDER DETAILS

                    Backendless.Data


                    orderDetails.saveAsync(object : AsyncCallback<OrderDetails>{

                        override fun handleFault(fault: BackendlessFault?) {
                            Toast.makeText(context,"NYKAT",Toast.LENGTH_LONG).show()

                        }

                        override fun handleResponse(response: OrderDetails?) {

                            //SAVE COMPLETED ^


                            orderDetailsList.add(response!!)

                            // ADDING RELATION : ORDER DETAILS TO ORDER (1:N)
                            Backendless.Persistence.of(Orders::class.java).addRelation(testOrder,"orderDetails",orderDetailsList,object : AsyncCallback<Int>{
                                override fun handleFault(fault: BackendlessFault?) {
                                    Toast.makeText(context,"NYKAT",Toast.LENGTH_LONG).show()
                                }

                                override fun handleResponse(response: Int?) {
                                    Toast.makeText(context,"ALL DONE ?",Toast.LENGTH_LONG).show()
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


        //FOR TEST PURPOSE - GETTING TABLE OBJECT COUNT

     Backendless.Data.of("DemoTable").getObjectCount(object : AsyncCallback<Int?> {
            override fun handleResponse(response: Int?) {

                val objectCount = response
                Log.d("TABLE OBJECT COUNT : ","$objectCount")

            }
            override fun handleFault(fault: BackendlessFault) {

                Toast.makeText(context,"retrieving table object count error : ${fault.message} ",Toast.LENGTH_SHORT).show()

            }
        })

        // TEST END

        // Getting Products List

        Backendless.Data.of(Products::class.java).find(object: AsyncCallback<List<Products>>{
            override fun handleFault(fault: BackendlessFault?) {

                Toast.makeText(context,"retrieving table error : ${fault?.message} ",Toast.LENGTH_SHORT).show()
            }

            override fun handleResponse(response: List<Products>?) {


                //setting adapter

                productsListSize = response!!.size

                producstList.adapter = productsAdapter(context,response!!)
            }


        })
    }

    companion object HomeFragCompanion {

        var productsListSize = 0

    }
}









