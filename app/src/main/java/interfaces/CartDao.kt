package interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import data.CartItem


@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): LiveData<List<CartItem>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Query("DELETE FROM cart_items where productId= :productId")
    suspend fun  removeItem(productId:Int)


    @Query("UPDATE cart_items SET quantity= :newQuantity WHERE productId= :productId")
    suspend fun updateQuantity(productId:Int,newQuantity:Int)
    @Query("DELETE FROM cart_items")
    suspend fun deleteAll()


    @Query("SELECT SUM(quantity) FROM cart_items")
     fun getTotalItems():LiveData<Int?>

    
     @Query("SELECT SUM(quantity*price) FROM cart_items")
     fun getTotalPrice():LiveData<Double?>




}
@Database(entities = [CartItem::class], version = 1)
abstract class CartDatabase:RoomDatabase(){
    abstract fun cartDao():CartDao

}