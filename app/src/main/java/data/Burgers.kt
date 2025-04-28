package data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//to parcelize the burger class in Android means to make it implement the parcelable Interface
//this allows instances of the burgers calass to be efficiently written to and read from a parcel
//the parcelis a container for a message that can be sent between different components of an Android Application
@Parcelize
data class Burgers(
  val id:Int,
  val name:String,
  val price:Double,
  val images:List<ImageSize> = emptyList(),
  val description:String,
  val data:List<Burgers> = emptyList()

):Parcelable