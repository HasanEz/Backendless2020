package com.example.backendless2020.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.backendless2020.R
import com.example.backendless2020.models.Category

class CategoryAdapter(val context: Context, val listCategory: List<Category>) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //pointers to layout items
        val categoryName = itemView.findViewById<TextView>(R.id.categoryTV)
        val categoryImage = itemView.findViewById<ImageView>(R.id.categoryImage)

        fun bindCategory(context: Context, category: Category) {

            categoryName.text = category.name
            categoryImage.setImageResource(category.image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, null)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindCategory(context,listCategory[position])
    }

}