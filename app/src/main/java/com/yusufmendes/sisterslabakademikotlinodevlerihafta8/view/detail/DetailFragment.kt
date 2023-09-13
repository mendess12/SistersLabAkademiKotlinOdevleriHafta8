package com.yusufmendes.sisterslabakademikotlinodevlerihafta8.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.R
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.databinding.FragmentDetailBinding
import com.yusufmendes.sisterslabakademikotlinodevlerihafta8.util.downloadFromUrl

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        val data = args.contentDetail

        binding.toolbarDetail.title = "${data.contentName}"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarDetail)

        data.contentImage?.let { binding.detailIv.downloadFromUrl(it) }
        binding.detailTv.text = data.contentDetail.toString()
    }
}