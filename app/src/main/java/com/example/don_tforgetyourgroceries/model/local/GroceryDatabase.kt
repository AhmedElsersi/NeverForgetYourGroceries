package com.example.don_tforgetyourgroceries.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.don_tforgetyourgroceries.model.entity.Grocery

private const val DATABASE_NAME = "grocery_database"

@Database(entities = [Grocery::class], version = 1, exportSchema = false)
abstract class GroceryDatabase:RoomDatabase() {

    abstract fun groceryDao(): GroceryDAO

    companion object {

        @Volatile
        private var  instance: GroceryDatabase? = null

        fun getInstance(context: Context): GroceryDatabase {
            return instance ?: synchronized(Any()){
                instance ?: buildDatabase(context).also{ instance = it}
            }
        }

        private fun buildDatabase(context: Context): GroceryDatabase {
            return Room.databaseBuilder(context.applicationContext, GroceryDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}