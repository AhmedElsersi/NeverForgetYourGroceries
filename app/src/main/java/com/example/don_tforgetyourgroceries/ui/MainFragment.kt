package com.example.don_tforgetyourgroceries.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.don_tforgetyourgroceries.R
import com.example.don_tforgetyourgroceries.databinding.FragmentMainBinding
import com.example.don_tforgetyourgroceries.model.entity.GridViewModal
import com.example.don_tforgetyourgroceries.model.entity.Grocery
import com.example.don_tforgetyourgroceries.ui.adapter.GridRVAdapter
import com.example.don_tforgetyourgroceries.ui.adapter.GroceryRecyclerView
import com.example.don_tforgetyourgroceries.ui.adapter.OnListItemClicked
import com.example.don_tforgetyourgroceries.ui.usersList.GroceriesViewModel

class MainFragment : Fragment(),OnListItemClicked {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: GroceriesViewModel

    private val groceryRecyclerView: GroceryRecyclerView by lazy {
        GroceryRecyclerView()
    }
    private lateinit var groceryGRV: GridView
    private lateinit var groceryList: List<GridViewModal>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(requireActivity())[GroceriesViewModel::class.java]
        binding.rvList.adapter = groceryRecyclerView
        groceryRecyclerView.onListItemClicked = this
        getAllGroceries()
        binding.fabAdd.setOnClickListener {
            binding.fabAdd.visibility=View.GONE
            binding.bottomGroceryCreate.visibility=View.VISIBLE
            binding.touchOutside.visibility=View.VISIBLE
        }
        binding.touchOutside.setOnClickListener {
            binding.fabAdd.visibility=View.VISIBLE
            binding.bottomGroceryCreate.visibility=View.GONE
            binding.touchOutside.visibility=View.GONE
        }
        var photo:Int=R.drawable.other
        binding.btnPhoto.setOnClickListener {
//            findNavController().navigate(R.id.action_mainFragment_to_imageFragment)
            binding.clPhotos.visibility=View.VISIBLE
            binding.tbPhoto.tvAppbar.setText(R.string.choose_photo)
            binding.clMain.visibility=View.GONE
        }
        // initializing variables of grid view with their ids.
        groceryGRV = view.findViewById(R.id.idGRV)
        groceryList = ArrayList()

        // on below line we are adding data to
        // our course list with image and course name.
        groceryList = groceryList + GridViewModal("Vegetables", R.drawable.vegetables)
        groceryList = groceryList + GridViewModal("Fruits", R.drawable.fruits)
        groceryList = groceryList + GridViewModal("Supermarkets", R.drawable.supermarkets)
        groceryList = groceryList + GridViewModal("Butchery", R.drawable.butchery)
        groceryList = groceryList + GridViewModal("Chicken", R.drawable.chicken)
        groceryList = groceryList + GridViewModal("Milk", R.drawable.milk)
        groceryList = groceryList + GridViewModal("Pharmacy", R.drawable.pharmacy)
        groceryList = groceryList + GridViewModal("Other", R.drawable.other)

        // on below line we are initializing our course adapter
        // and passing course list and context.
        val courseAdapter = context?.let { GridRVAdapter(courseList = groceryList, it) }

        // on below line we are setting adapter to our grid view.
        groceryGRV.adapter = courseAdapter
        // on below line we are adding on item
        // click listener for our grid view.
        groceryGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // inside on click method we are simply displaying
            // a toast message with course name.
            Toast.makeText(
                context, groceryList[position].groceryName + " selected",
                Toast.LENGTH_SHORT
            ).show()
            binding.clPhotos.visibility=View.GONE
            binding.clMain.visibility=View.VISIBLE
            binding.btnPhoto.setBackgroundResource(groceryList[position].groceryPhoto)
            photo=groceryList[position].groceryPhoto
//            findNavController().navigate(R.id.action_imageFragment_to_mainFragment)

        }
        var unit = ""
        binding.spinnerUnit.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                unit= adapterView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                unit=adapterView?.getItemAtPosition(0).toString()
            }

        }
        binding.btnSubmit.setOnClickListener {
            val grocery:String=binding.etiGrocery.text.toString()
            val quantity:String=binding.etiQuantity.text.toString()
            // local
            viewModel.addGrocery(
                Grocery(0,
                    grocery,
                    photo,
                    quantity,
                    unit)
            )
            getAllGroceries()
            view.hideKeyboard()
            binding.bottomGroceryCreate.visibility=View.GONE
            binding.touchOutside.visibility=View.GONE
            binding.fabAdd.visibility=View.VISIBLE
            getAllGroceries()
            binding.etiGrocery.setText("")
            binding.etiQuantity.setText("")
        }
        // for local database
        viewModel.groceryLiveData.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrEmpty()){
                groceryRecyclerView.setList(it)
                binding.progressBar.visibility = View.GONE
            }else if (it.isEmpty()){
                groceryRecyclerView.setList(emptyList())
                binding.progressBar.visibility = View.GONE
            }
        })

    }

    private fun getAllGroceries() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getGroceries()
    }

    private fun View.hideKeyboard(){
        val hideKeyboard = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        hideKeyboard.hideSoftInputFromWindow(windowToken,0)}

    override fun onItemClicked(grocery: Grocery) {
        viewModel.deleteGrocery(grocery)
        Toast.makeText(context, "The user ${grocery.name} is deleted successfully",
            Toast.LENGTH_SHORT).show()
        getAllGroceries()
    }
}
