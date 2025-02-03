package com.example.hotel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotel.ui.theme.Dessert
import com.example.hotel.ui.theme.Pizza

class PizzaDessertAdapter (private val items:List<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
 companion object{
     private const val VIEW_TYPE_PIZZA=1
     private const val VIEW_TYPE_DESSERT=2

 }












    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater= LayoutInflater.from(parent.context)
        return when(viewType){
            VIEW_TYPE_PIZZA->{
                val view=inflater.inflate(R.layout.items,parent,false)
                    PizzaViewHolder(view)
            }
            VIEW_TYPE_DESSERT ->{
                val view= inflater.inflate(R.layout.item_dessert, parent, false)
                DessertViewHolder(view)
            }
            else ->throw IllegalArgumentException("Invalid view type")
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when (holder.itemViewType){
           VIEW_TYPE_PIZZA ->{
               val pizza =items[position] as Pizza
               (holder as PizzaViewHolder).bind(pizza)
           }
           VIEW_TYPE_DESSERT->{
               val dessert= items[position] as Dessert
               (holder as DessertViewHolder).bind(dessert)
           }
       }
    }
 override fun getItemCount():Int= items.size
    override fun getItemViewType(position: Int): Int {
        return when (items[position]){
            is Pizza -> VIEW_TYPE_PIZZA
            is Dessert -> VIEW_TYPE_DESSERT
            else ->throw
                    IllegalArgumentException("Invalid item type")
        }
    }
    class PizzaViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val pizzaImageView: ImageView = itemView.findViewById(R.id.pizzaImageView)
        private val pizzaNameTextView:TextView= itemView.findViewById(R.id.pizzaNameTextView)

        fun bind(pizza: Pizza){
            pizzaNameTextView.text= pizza.name
            Glide.with(itemView.context)
                .load(pizza.imageUrls)
                .into(pizzaImageView)

        }

    }
    class DessertViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val dessertImageView: ImageView= itemView.findViewById(R.id.dessertImageView)
        private val dessertNameTextView:TextView= itemView.findViewById(R.id.dessertNameTextView)

        fun bind(dessert: Dessert){
            dessertNameTextView.text= dessert.name
            Glide.with(itemView.context)
                .load(dessert.imageUrls)
                .into(dessertImageView)
        }
    }

}