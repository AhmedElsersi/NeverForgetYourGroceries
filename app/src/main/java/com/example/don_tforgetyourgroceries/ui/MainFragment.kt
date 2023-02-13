package com.example.don_tforgetyourgroceries.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.don_tforgetyourgroceries.databinding.FragmentMainBinding
import com.example.don_tforgetyourgroceries.ui.adapter.GroceryRecyclerView
import com.example.don_tforgetyourgroceries.ui.usersList.GroceriesViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    lateinit var viewModel: GroceriesViewModel

    private val groceryRecyclerView: GroceryRecyclerView by lazy {
        GroceryRecyclerView()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("Range", "UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tbMain.tvAppbar.textSize = 30f


    }
}
