package data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
data class CartItem(
  @PrimaryKey val productId:Int,
  val name:String,
  val burger:Burgers,
  var quantity:Int=1
):Parcelable