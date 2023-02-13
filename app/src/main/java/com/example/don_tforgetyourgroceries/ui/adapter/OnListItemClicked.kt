package com.example.don_tforgetyourgroceries.ui.adapter

import com.example.don_tforgetyourgroceries.model.entity.Grocery

interface OnListItemClicked {
    fun onItemClicked(grocery: Grocery)
}