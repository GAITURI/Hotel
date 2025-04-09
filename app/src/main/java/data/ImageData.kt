package data



data class ImageData (
    val smObject:ImageObject?,
    val lgObject:ImageObject?
)
data class ImageObject(
    val sm:String?,
    val lg:String?
)