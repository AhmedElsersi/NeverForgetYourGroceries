package com.example.don_tforgetyourgroceries.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.don_tforgetyourgroceries.R
import com.example.don_tforgetyourgroceries.model.entity.Grocery

class GroceryRecyclerView : RecyclerView.Adapter<GroceryRecyclerView.GroceryViewHolder>() {

    private var groceryList:List<Grocery> = emptyList()
    var onListItemClicked : OnListItemClicked?= null

    @SuppressLint("NotifyDataSetChanged")
    fun setList(groceries:List<Grocery>){
        this.groceryList = groceries
        notifyDataSetChanged()
    }


    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var iviPhoto: ImageView =itemView.findViewById(R.id.iviPhoto)
        private var tviName: TextView =itemView.findViewById(R.id.tviGrocery)
        private var tviQuantity:TextView=itemView.findViewById(R.id.tviQuantity)
        private var tviUnit:TextView=itemView.findViewById(R.id.tviUnit)

        fun bind(grocery: Grocery){
            iviPhoto.setImageResource(grocery.imageId)
            tviName.text= grocery.name
            tviQuantity.text= grocery.quantity
            tviUnit.text= grocery.unit
            itemView.setOnClickListener {
                onListItemClicked?.onItemClicked(grocery)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return GroceryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return groceryList.size
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val grocery:Grocery = groceryList[position]
        holder.bind(grocery)
    }

}