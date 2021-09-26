package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.recipefinder.R
import com.example.recipefinder.databinding.DetailFragmentBinding
import com.example.recipefinder.getViewModel

class DetailFragment : Fragment() {
    private lateinit var mViewModel: DetailViewModel
    private lateinit var binding: DetailFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel = getViewModel { DetailViewModel() }
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        return binding.root
    }
}