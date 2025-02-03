package com.example.hotel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotel.ui.theme.Burgers

class PizzaDessertAdapter(private val items: List<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
 companion object{
     private const val VIEW_TYPE_BURGERS=1


 }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        return when(viewType){
            VIEW_TYPE_BURGERS->{
                val view=inflater.inflate(R.layout.items,parent,false)
                    PizzaViewHolder(view)
            }
            else ->throw IllegalArgumentException("Invalid view type")
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when (holder.itemViewType){
           VIEW_TYPE_BURGERS->{
               val burgers =items[position] as Burgers
               (holder as PizzaViewHolder).bind(burgers)
           }

       }
    }
 override fun getItemCount():Int= items.size
    override fun getItemViewType(position: Int): Int {
        return when (items[position]){

            else -> VIEW_TYPE_BURGERS
        }
    }
    class PizzaViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val pizzaImageView: ImageView = itemView.findViewById(R.id.pizzaImageView)
        private val pizzaNameTextView:TextView= itemView.findViewById(R.id.pizzaNameTextView)

        fun bind(burgers: Burgers){
            pizzaNameTextView.text= burgers.name
            Glide.with(itemView.context)
                .load(burgers.imageUrls)
                .into(pizzaImageView)

        }

    }

}