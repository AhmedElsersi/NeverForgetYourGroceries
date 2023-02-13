package com.example.don_tforgetyourgroceries.model.local

import com.example.don_tforgetyourgroceries.model.entity.Grocery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LocalRepositoryImp(private val db: GroceryDatabase) : LocalRepository {
    override suspend fun insertOrUpdateGrocery(grocery: Grocery) {
        withContext(Dispatchers.IO) {
            db.groceryDao().insertOrUpdateGrocery(grocery)
        }
    }

    override suspend fun deleteGrocery(grocery: Grocery) {
        withContext(Dispatchers.IO) {
            db.groceryDao().deleteGrocery(grocery)
        }
    }

    override suspend fun getGroceries() = withContext(Dispatchers.IO) {
            db.groceryDao().getGroceries()
        }
}