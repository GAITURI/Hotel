package com.example.hotel.utils

import android.content.Context
import androidx.room.Room
import interfaces.CartDatabase

object CartDatabaseInstance {
    @Volatile
    private var INSTANCE: CartDatabase?= null
    fun getDatabase(context:Context):CartDatabase {
        return INSTANCE?: synchronized(this){
            val instance= Room.databaseBuilder(
                context.applicationContext,
                CartDatabase::class.java,
                "cart_database"
            ).build()
            INSTANCE= instance
            instance
        }
    }
}