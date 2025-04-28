package data

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
data class CartItem(
    val burger: Burgers,
    var quantity:Int=1
):Parcelable