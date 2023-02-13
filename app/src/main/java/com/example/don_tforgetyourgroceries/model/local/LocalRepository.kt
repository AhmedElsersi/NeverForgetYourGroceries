package com.example.don_tforgetyourgroceries.model.local

import com.example.don_tforgetyourgroceries.model.entity.Grocery


interface LocalRepository {

    suspend fun insertOrUpdateGrocery(grocery: Grocery)

    suspend fun deleteGrocery(grocery: Grocery)

    suspend fun getGroceries():List<Grocery>
}