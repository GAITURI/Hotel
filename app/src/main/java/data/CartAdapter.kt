package com.example.hotel.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.animation.core.infiniteRepeatable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotel.R
import com.example.hotel.databinding.CartCheckoutBinding
import data.CartItem

class CartAdapter (val context: Context, val cartItems:List<CartItem>): ListAdapter<CartItem,CartAdapter.CartViewHolder>(DiffCallback){

        init{
            submitList(cartItems)
        }


    class CartViewHolder(private val binding:CartCheckoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem:CartItem){
            val binding=CartCheckoutBinding.bind(itemView)
            binding.tvCartItemName.text=cartItem.burger.name
            binding.tvCartItemPrice.text="Ksh.${cartItem.burger.price}"
            binding.tvCartItemQuantity.text="x${cartItem.quantity}"
            val imageSize= cartItem.burger.images?.firstOrNull()?.sm
            Glide.with(binding.root.context)
                .load(imageSize)
                .placeholder(R.drawable.meal)
                .centerCrop()
                .into(binding.ivCartItemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val binding=CartCheckoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItemObject= getItem(position)
        holder.bind(cartItemObject)
        
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
        //a companion object allows you to define properties and functions that are logically associated with the class itself
       //diffutil is a utility class from the androidx.recyclerview.widget library that helps efficiently update the contents of  a recylcer view when the underlying data changes
        //it  does this by calculating the minimal set of changes between two lists of data
        //diffutil.itemcallback<cart iem> is an interface that you need to implement to tell ddiffutil how to compatre items in your list
        //the <CartItem> specifies that this callback is for comparing CartItem objects
        companion object DiffCallback: DiffUtil.ItemCallback<CartItem>(){
            override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return oldItem.burger.id==newItem.burger.id

            }

            override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return oldItem ==newItem
            }

        }
}
