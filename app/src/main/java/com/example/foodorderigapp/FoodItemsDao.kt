package com.example.foodorderigapp

import androidx.room.*

@Dao
interface FoodItemsDao {
    @Insert
    suspend fun insertCartItem(cartItem: FoodItems)

    @Update
    suspend fun updateCartItem(cartItem: FoodItems)

    @Delete
    suspend fun deleteCartItem(cartItem: FoodItems)

    @Query("SELECT * FROM product")
    suspend fun getAllCartItems(): List<FoodItems>
}





