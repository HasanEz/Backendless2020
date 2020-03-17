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
import com.example.backendless2020.models.Orders
import com.example.backendless2020.models.Products
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = "Products" })

        root.findViewById<Button>(R.id.btnOrder)?.setOnClickListener {


            // saving order (sending)

            var testOrder = Orders()
             val orderCollection = mutableListOf<Orders>()




            testOrder.clientName =  Backendless.UserService.CurrentUser().getProperty("name").toString()
            orderCollection.add(testOrder)



            testOrder.saveAsync(object: AsyncCallback<Orders>{

                override fun handleFault(fault: BackendlessFault?) {
                    Toast.makeText(context,"error:${fault?.message}",Toast.LENGTH_LONG).show()
                }

                override fun handleResponse(response: Orders?) {

                    Toast.makeText(context,"saved",Toast.LENGTH_SHORT).show()

                    Backendless.Data.of(BackendlessUser::class.java).setRelation(
                        Backendless.UserService.CurrentUser(),
                        "Orders",
                        orderCollection,object : AsyncCallback<Int>{
                            override fun handleFault(fault: BackendlessFault?) {

                                Toast.makeText(context,"nykat : ${fault?.message}",Toast.LENGTH_LONG).show()


                            }

                            override fun handleResponse(response: Int?) {
                              Toast.makeText(context,"relation set",Toast.LENGTH_SHORT).show()
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

                producstList.adapter = productsAdapter(context,response!!)
            }


        })







    }
}
