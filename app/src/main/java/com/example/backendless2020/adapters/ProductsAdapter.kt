package com.example.backendless2020.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.backendless2020.R
import com.example.backendless2020.models.OrderDetails
import com.example.backendless2020.models.Products
import com.example.backendless2020.ui.home.HomeFragment

class ProductsAdapter(private val context: Context, private val productsList: List<Products>): BaseAdapter (){


    // Amount array to hold proudct order amount
    var amountsArray = IntArray(productsList.size)


    override fun getItem(position: Int): Any {
        return productsList[position]
    }

    override fun getItemId(position: Int): Long {

        return position.toLong()
    }

    override fun getCount(): Int {

        return productsList.count()

    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.item_product, null )


        Log.d("Binding","$position")

        //Getting Product Data / Properties

        val price = productsList[position].price
        val product = productsList[position].product

        //order detail instance
        var orderDetails = OrderDetails()


        //Pointers to product list item

        val productNameTV = view.findViewById<TextView>(R.id.tvProductName)
        val priceTV = view.findViewById<TextView>(R.id.tvPrice)
        val btnPlus = view.findViewById<Button>(R.id.btnPlus)
        val btnMinus = view.findViewById<Button>(R.id.btnMinus)

        val amountET = view.findViewById<EditText>(R.id.etAmount)

        //setting product data

        priceTV.text = price.toString()
        productNameTV.text = product

        // Amounts edittext to save amounts

        amountET.setOnFocusChangeListener { v, hasFocus ->

            try {
                if (!hasFocus){

                    amountsArray[position] = amountET.text.toString().toInt()

                    orderDetails.amount = amountsArray[position]
                    orderDetails.price = productsList[position].price
                    orderDetails.product = productsList[position].product
                    orderDetailsList[position] = orderDetails



                }
            } catch (e: Exception) {
            }
        }



        amountET.setText(amountsArray[position].toString(), TextView.BufferType.EDITABLE)
//

        //BUTTONS LOGIC


        btnPlus.setOnClickListener {

            Toast.makeText(context,"btn plus clicked",Toast.LENGTH_SHORT).show()

            amountsArray[position]++
            amountET.setText(amountsArray[position].toString(), TextView.BufferType.EDITABLE)


            //test creating order details

            orderDetails.amount = amountsArray[position]
            orderDetails.price = productsList[position].price
            orderDetails.product = productsList[position].product
            orderDetailsList[position] = orderDetails

        }

        btnMinus.setOnClickListener {



            if (amountsArray[position] > 0 ) {


                amountsArray[position]--
                amountET.setText(amountsArray[position].toString(), TextView.BufferType.EDITABLE)

                orderDetails.amount = amountsArray[position]
                orderDetails.price = productsList[position].price
                orderDetails.product = productsList[position].product
                orderDetailsList[position] = orderDetails

            }

            Toast.makeText(context,"btn plus clicked",Toast.LENGTH_SHORT).show()

        }




        return view
    }

    companion object testComp {

        // Initialized from home fragment with products list size

        var orderDetailsList = mutableListOf<OrderDetails>()


        }

    }


