package com.example.don_tforgetyourgroceries.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_table")
data class Grocery (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "grocery")
    var name:String,
    var message: String,
    var imageId:Int
        )