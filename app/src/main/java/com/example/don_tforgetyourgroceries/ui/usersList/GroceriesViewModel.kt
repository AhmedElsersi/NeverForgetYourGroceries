package com.example.don_tforgetyourgroceries.ui.usersList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.don_tforgetyourgroceries.model.entity.Grocery
import com.example.don_tforgetyourgroceries.model.local.GroceryDatabase
import com.example.don_tforgetyourgroceries.model.local.LocalRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceriesViewModel(application: Application):AndroidViewModel(application) {
    // local implementations
    private var localRepositoryImp: LocalRepositoryImp
    private var groceryMutableLiveData = MutableLiveData<List<Grocery>>()
    val groceryLiveData: LiveData<List<Grocery>> get() = groceryMutableLiveData
    init {
        val db = GroceryDatabase.getInstance(application)
        localRepositoryImp= LocalRepositoryImp(db)
    }

    fun getGroceries()= viewModelScope.launch{
        groceryMutableLiveData.postValue(localRepositoryImp.getGroceries())
    }
    fun addGrocery(grocery: Grocery){
        viewModelScope.launch (Dispatchers.IO){
            localRepositoryImp.insertOrUpdateGrocery(grocery)
        }
    }
    fun deleteGrocery(grocery: Grocery){
        viewModelScope.launch (Dispatchers.IO){
            localRepositoryImp.deleteGrocery(grocery)
        }
    }
}