package data

import android.media.Image
import com.google.gson.annotations.SerializedName

data class Burgers(
  val id:Int,
  val name:String,
  @SerializedName("images")
  val images:List<ImageData>,
  val description:String,
  val data:List<Burgers>

)