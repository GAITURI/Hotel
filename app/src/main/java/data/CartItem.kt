package data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cart_items")
data class CartItem(
  @PrimaryKey val productId:Int,
  val name:String,
  val burger:Burgers,
  var quantity:Int=1
)