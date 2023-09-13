package com.yusufmendes.sisterslabakademikotlinodevlerihafta8.view.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.R
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.adapter.ContentAdapter
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.databinding.FragmentListBinding
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.model.Content

class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private lateinit var contentAdapter: ContentAdapter
    private val viewModel: ListViewModel by viewModels()
    private val args: ListFragmentArgs by navArgs()
    private lateinit var refContent: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        val data = args.categoryName

        binding.toolbarList.title = "$data"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarList)

        val database = FirebaseDatabase.getInstance()
        refContent = database.getReference(data!!)

        binding.listRv.setHasFixedSize(true)
        binding.listRv.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        viewModel.getContent(refContent)
        observeLiveData()

    }

    private fun navigateToDetail(content: Content) {
        val action = ListFragmentDirections.navigateToDetail(content)
        findNavController().navigate(action)
    }

    private fun observeLiveData() {
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                contentAdapter = ContentAdapter(it, ::navigateToDetail)
                binding.listRv.adapter = contentAdapter
            } else {
                Snackbar.make(requireView(), "List is empty!!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}