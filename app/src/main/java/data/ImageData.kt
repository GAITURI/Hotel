package data



data class ImageData (
    val sm: String?

){
    override fun hashCode(): Int {
        var result= 0
        if(sm!=null ){
            result= sm.hashCode()
        }
        return result
    }
}