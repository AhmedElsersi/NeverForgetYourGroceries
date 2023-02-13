package com.example.don_tforgetyourgroceries.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.don_tforgetyourgroceries.model.entity.Grocery

@Dao
interface GroceryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateGrocery(grocery: Grocery)

    @Delete
    suspend fun deleteGrocery(grocery: Grocery)

    @Query("select * from grocery_table")
    suspend fun getGroceries():List<Grocery>
}