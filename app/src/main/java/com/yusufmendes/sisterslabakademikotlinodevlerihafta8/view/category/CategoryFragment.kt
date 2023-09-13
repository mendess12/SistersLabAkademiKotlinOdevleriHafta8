package com.yusufmendes.sisterslabakademikotlinodevlerihafta8.view.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.R
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.adapter.CategoryAdapter
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.databinding.FragmentCategoryBinding
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.model.Category


class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding: FragmentCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var refCategory: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryBinding.bind(view)

        binding.toolbarCategory.title = "Category"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarCategory)

        val database = FirebaseDatabase.getInstance()
        refCategory = database.getReference("category")

        binding.categoryRv.setHasFixedSize(true)
        binding.categoryRv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getCategory(refCategory)
        observeLiveData()
    }

    private fun navigateToLis(category: Category) {
        val action = CategoryFragmentDirections.navigateToList(category.categoryName)
        findNavController().navigate(action)
    }

    private fun observeLiveData() {
        viewModel.categoryLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                categoryAdapter = CategoryAdapter(it, ::navigateToLis)
                binding.categoryRv.adapter = categoryAdapter
            } else {
                Snackbar.make(requireView(), "List is empty!!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}