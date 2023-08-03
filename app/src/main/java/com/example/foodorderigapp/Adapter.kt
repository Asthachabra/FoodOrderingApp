
package com.example.foodorderigapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")

class Adapter(private val listener: MainActivity): RecyclerView.Adapter<FoodViewHolder>() {

    private val items:ArrayList<Food> =ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_food,parent,false)
        val viewHolder=FoodViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentItem=items[position]
        holder.titleView.text=currentItem.title
        holder.description.text=currentItem.description
        holder.cost.text=currentItem.cost
        Glide.with(holder.itemView.context).load(currentItem.image_Url).into(holder.image)
        holder.button.setOnClickListener {
            val clickedItem = items[position]
            listener.onItemClicked(clickedItem)
        }
        holder.button2.setOnClickListener {
            val clickedItem = items[position]
            listener.onItemClicked(clickedItem)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateFood(updatedFood:ArrayList<Food>){
        items.clear()
        items.addAll(updatedFood)

        notifyDataSetChanged()
    }
}

class FoodViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val titleView:TextView=itemView.findViewById(R.id.title)
    val image: ImageView =itemView.findViewById(R.id.image)
    val description:TextView=itemView.findViewById(R.id.description)
    val cost:TextView=itemView.findViewById(R.id.cost)
    val button: Button =itemView.findViewById(R.id.decrementButton)
    val button2: Button =itemView.findViewById(R.id.incrementButton)
}

interface FoodItemClicked{
    fun onItemClicked(item:Food)
    fun Food( title: String, description: String,cost: String,image_Url:String): Food
}