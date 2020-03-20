package com.example.backendless2020.ui

import com.example.backendless2020.adapters.ProductsAdapter
import com.example.backendless2020.models.OrderDetails

fun main (){


    var orderDetailsList = mutableListOf<OrderDetails>()
    var orderDetailsList2 = mutableListOf<String>()

    orderDetailsList2.add("element")
    orderDetailsList2.add("element2")
    orderDetailsList2.add("element3")


    orderDetailsList2.removeAll { it == "element3" }

    print(orderDetailsList2)


    var orderDetails = OrderDetails()
    var orderDetails2 = OrderDetails()
    orderDetails.amount = 10
    orderDetails2.amount = 5
    orderDetailsList.add(orderDetails)
    orderDetailsList.add(orderDetails2)


    var iterator = orderDetailsList.iterator()


//    orderDetailsList.removeAll { it.amount.toString() == "0"|| it.amount.toString() == "" || it.amount == null}


    iterator.forEach {

        print(it.amount)
        if (it.amount == 5){iterator.remove()}
    }



//    iterator.forEach {
//       if(it.amount == 0){
//           iterator.remove()
//       }
//    }


//    for(i in 0 until 9){
//
//        orderDetailsList.add(i, OrderDetails())
//
//    }

    print( orderDetailsList.size)
    print( orderDetailsList)


}