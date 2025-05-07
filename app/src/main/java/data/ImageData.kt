package data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



@Parcelize
data class ImageSize(
    val sm:String?=null
):Parcelable