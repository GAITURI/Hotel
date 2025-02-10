package com.example.hotel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.hotel.ui.theme.Burgers


//declare the pizzadessertAdapterClass
//private var burgers:List<Burgers is the list of burger objectss that the adapter will display
//

class PizzaDessertAdapter(private var burgers: List<Burgers>): RecyclerView.Adapter<PizzaDessertAdapter.PizzaDessertViewHolder>(){
 companion object{
     private const val VIEW_TYPE_BURGERS=1


 }
    //the ViewHolder class
    class PizzaDessertViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
         val pizzaImageView: ImageView = itemView.findViewById(R.id.pizzaImageView)
      val pizzaNameTextView:TextView= itemView.findViewById(R.id.pizzaNameTextView)



    }

    //this method is called when the RecyclerView needs a new viewholder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaDessertViewHolder{
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        return PizzaDessertViewHolder(itemView)


    }


//this method is called to bind data to a viewholder
    override fun onBindViewHolder(holder: PizzaDessertViewHolder, position: Int) {
        val currentBurger= burgers[position]
    holder.pizzaNameTextView.text=currentBurger.name

    //Gliding
    val requestOptions= RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()

    Glide.with(holder.itemView.context)
        .load(currentBurger.imageUrls)
        .apply(requestOptions)
        .into(holder.pizzaImageView)


    }
 override fun getItemCount():Int{
     return burgers.size
 }
    fun updateBurgers(newBurgers:List<Burgers>){
        burgers=newBurgers
        notifyDataSetChanged()

    }


}