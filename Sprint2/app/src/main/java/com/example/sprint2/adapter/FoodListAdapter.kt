package com.example.sprint2.adapter

import android.content.Context
import com.example.sprint2.ui.MainActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint2.R
import com.example.sprint2.model.FoodItem
import com.example.sprint2.ui.selectedList
import kotlinx.android.synthetic.main.example_food_item_layout.view.*
import java.security.AccessController.getContext

class FoodListAdapter(val data: ArrayList<FoodItem>): RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.cv_iv
        val nameTextView: TextView = view.cv_tv
        val switch: Switch = view.cv_switch
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.example_food_item_layout, parent, false)
        ViewHolder(viewGroup)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
      return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(data[position].Icon())
        holder.nameTextView.text = data[position].Name()

        holder.switch.setOnClickListener { p0 ->

            if(!selectedList.contains(data[position].Name()) && holder.switch.isChecked) {
                selectedList.add(data[position].Name())
                println(selectedList)

                Toast.makeText(p0.context, "${holder.nameTextView.text} added to shopping list!",Toast.LENGTH_SHORT).show()
            } else {
                if(selectedList.contains(data[position].Name()) && !holder.switch.isChecked) {
                    selectedList.remove(data[position].Name())
                Toast.makeText(p0.context, "${holder.nameTextView.text} removed from shopping list!", Toast.LENGTH_SHORT).show()
                }
                println(selectedList)
            }
        }
    }
}