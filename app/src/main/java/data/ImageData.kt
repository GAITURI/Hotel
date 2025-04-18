package data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ImageData (
    val smObject:ImageObject?,
    val lgObject:ImageObject?
):Parcelable
@Parcelize
data class ImageObject(
    val sm:String?,
    val lg:String?
):Parcelable