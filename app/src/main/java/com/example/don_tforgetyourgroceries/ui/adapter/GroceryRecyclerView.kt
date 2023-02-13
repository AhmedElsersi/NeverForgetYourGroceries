package com.example.don_tforgetyourgroceries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.don_tforgetyourgroceries.R
import com.example.don_tforgetyourgroceries.model.entity.Grocery

class GroceryRecyclerView : RecyclerView.Adapter<GroceryRecyclerView.userViewHolder>() {

    var groceryList:List<Grocery> = emptyList()
    var onListItemClicked : OnListItemClicked?= null

    fun setList(groceries:List<Grocery>){
        this.groceryList = groceries
        notifyDataSetChanged()
    }

//    lateinit var binding: u

    inner class userViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
/*        var iviPhoto:ImageView=itemView.findViewById(R.id.iviPhoto)
        var tviName:TextView=itemView.findViewById(R.id.tviName)
        var tviMessage:TextView=itemView.findViewById(R.id.tviMessage)

        fun bind(grocery: Grocery){
            iviPhoto.setImageResource(grocery.imageId)
            tviName.text= grocery.name
            tviMessage.text= grocery.message
            itemView.setOnClickListener {
                onListItemClicked?.onItemClicked(grocery)
            }
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return userViewHolder(view)
    }

    override fun getItemCount(): Int {
        return groceryList.size
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        var grocery:Grocery = groceryList[position]
//        holder.bind(grocery)
    }

}