package data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//to parcelize the burger class in Android means to make it implement the parcelable Interface
//this allows instances of the burgers calass to be efficiently written to and read from a parcel
//the parcelis a container for a message that can be sent between different components of an Android Application

@Parcelize
data class Burgers(
  val id:Int,
  val name:String,
  val images:List<ImageData>,
  val description:String,
  val price:Double?= null,
  val data:List<Burgers>

):Parcelable