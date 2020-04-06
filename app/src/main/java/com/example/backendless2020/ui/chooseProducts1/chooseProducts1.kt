package com.example.backendless2020.ui.chooseProducts1

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.backendless2020.R
import com.example.backendless2020.adapters.CategoryAdapter
import com.example.backendless2020.models.Category
import kotlinx.android.synthetic.main.fragment_choose_products1.*
import kotlinx.android.synthetic.main.fragment_choose_products1.view.*

/**
 * A simple [Fragment] subclass.
 */
class chooseProducts1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_choose_products1, container, false)


        val category1 = Category("Drinks",R.drawable.ic_001_soft_drink)
        val category2 = Category("Cleaners",R.drawable.ic_002_bleach)
        val category3 = Category("Cigs",R.drawable.ic_004_cigarettes)
        val category4 = Category("Milk",R.drawable.ic_005_milk)
        val category5 = Category("Sale",R.drawable.ic_006_sale)

        val categoryList = mutableListOf<Category>()

        categoryList.add(category1)
        categoryList.add(category2)
        categoryList.add(category3)
        categoryList.add(category4)
        categoryList.add(category5)


        view.findViewById<RecyclerView>(R.id.categoryLV).adapter = CategoryAdapter(context!!,categoryList)
        val layoutManger = LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.categoryLV).layoutManager = layoutManger


        return view
    }

    override fun onAttach(context: Context) {


        super.onAttach(context)


    }
}