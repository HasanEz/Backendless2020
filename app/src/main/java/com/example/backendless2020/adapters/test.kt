package com.example.backendless2020.adapters

import com.example.backendless2020.models.OrderDetails

object test {
    @JvmStatic
    fun main(args: Array<String>) {



        val user = "mostafa abo zbr"
        user.toCharArray()

        var userIntCode = ""


        for (i in user.toCharArray().indices){

        println(user.toCharArray().get(i).toInt())

            userIntCode += "${user.toCharArray()[i].toInt()}"

        }


        print(userIntCode.toLong() + 1)



    }
}