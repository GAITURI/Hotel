package com.example.hotel

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.hotel.data.Burgers


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
    val  imageUrl=currentBurger.getImageUrl()
    Log.d("PizzaDessertAdapter", "Burger name: ${currentBurger.name}")
    Log.d("PizzaDessertAdapter", "Image Url:$imageUrl}")
    holder.pizzaNameTextView.text=currentBurger.name

    //Gliding
    val requestOptions= RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .centerCrop()
if(imageUrl.isNullOrEmpty()) {
    Glide.with(holder.itemView.context)
        .load(R.drawable.meal)
        .apply(requestOptions)
        .into(holder.pizzaImageView)
}else{
    Glide.with(holder.itemView.context)
        .load(imageUrl)
        .apply(requestOptions)
        .listener(object:RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean {
                Log.e("PizzaDessertAdapter", "Glide load failed for URL: $imageUrl", e)
                return false
            }

            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target:Target<Drawable>?,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                Log.d("PizzaDessertAdapter", "Glide load successful for URL: $imageUrl")
                return false
            }
        })
        .into(holder.pizzaImageView)

}

    }
 override fun getItemCount():Int{
     return burgers.size
 }
    fun updateBurgers(newBurgers:List<Burgers>){
        burgers=newBurgers
        notifyDataSetChanged()

    }


}