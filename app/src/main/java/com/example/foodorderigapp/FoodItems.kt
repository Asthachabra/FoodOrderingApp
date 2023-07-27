package com.example.foodorderigapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "product")
data class FoodItems(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title: String,
    val cost: String,
    var quantity: Int
)