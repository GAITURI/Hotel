package com.example.hotel.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotel.R
import com.example.hotel.databinding.ItemsBinding
import data.Burgers
import data.CartItem


//declare the pizzadessertAdapterClass
//private var burgers:List<Burgers is the list of burger objectss that the adapter will display
//the constructor takes a list of burgers as a parameter, the list will be used to populate the recycler view
//the adapter inherits from the recycler view adapter
class PizzaDessertAdapter(private val burgers : List<Burgers>, private val onAddToCartClicked:(cartItem:CartItem)->Unit) :RecyclerView.Adapter<PizzaDessertAdapter.BurgerViewHolder>(){
private var listData:MutableList<Burgers>  = burgers as MutableList<Burgers>
        var selectedList= mutableListOf<Int>()
    private var cartItems: ArrayList<CartItem> = ArrayList()
fun updateCartItems(newCartItems:ArrayList<CartItem>)
{
    cartItems= newCartItems
    notifyDataSetChanged()
}
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
            //the listener takes a Burger Object
        val addToCartButton: Button = holder.itemView.findViewById(R.id.btnAddToCart)
        addToCartButton.setOnClickListener{
            val cartItem= CartItem(burgers[position].id,burgers[position].name,burgers[position])
            updateButtonState(addToCartButton,false)
            onAddToCartClicked(cartItem)

        }
    }
    private fun updateButtonState(button:Button,isInCart:Boolean){
        if(isInCart){
            button.text= button.context.getString(R.string.remove_from_cart)
            button.setBackgroundResource(R.drawable.remove_from_cart)

        }else{
            button.text= button.context.getString(R.string.add_to_cart)
            button.setBackgroundResource(R.drawable.rounded_view_for_menu_red)
        }
    }
}
