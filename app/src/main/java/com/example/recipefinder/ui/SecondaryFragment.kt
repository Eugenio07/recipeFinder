package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.recipefinder.R
import com.example.recipefinder.databinding.SecondaryFragmentBinding
import com.example.recipefinder.getViewModel

class SecondaryFragment : Fragment() {
    private lateinit var mViewModel: SecondaryViewModel
    private lateinit var binding: SecondaryFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel = getViewModel { SecondaryViewModel() }
        binding = DataBindingUtil.inflate(inflater, R.layout.secondary_fragment, container, false)
        binding.viewModel = mViewModel
        return binding.root
    }
}