package com.example.hotel.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotel.R
import com.example.hotel.databinding.ItemsBinding
import data.Burgers


//declare the pizzadessertAdapterClass
//private var burgers:List<Burgers is the list of burger objectss that the adapter will display
//the constructor takes a list of burgers as a parameter, the list will be used to populate the recycler view
//the adapter inherits from the recycler view adapter
class PizzaDessertAdapter(private val burgers : List<Burgers>) :RecyclerView.Adapter<PizzaDessertAdapter.BurgerViewHolder>(){
private var listData:MutableList<Burgers>  = burgers as MutableList<Burgers>
        var selectedList= mutableListOf<Int>()

    inner class BurgerViewHolder(val view:View):RecyclerView.ViewHolder(view){
        fun bind(burger:Burgers){
            //get the reference to the views in the items.xml layout file
            val binding= ItemsBinding.bind(view)
            binding.tvTitle.text=burger.name
            binding.tvPrice.text=burger.price.toString()
            Glide.with(view.context)
                .load(burger.images)
                .placeholder(R.drawable.meal)
                .centerCrop()
                .into(binding.imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurgerViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        return BurgerViewHolder(v)
    }

    override fun getItemCount(): Int {
        return burgers.size
    }
    override fun onBindViewHolder(holder: BurgerViewHolder, position: Int) {
        holder.bind(burgers[position])
    }

}
