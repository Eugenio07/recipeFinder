package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.recipefinder.R
import com.example.recipefinder.databinding.ListFragmentBinding
import com.example.recipefinder.getViewModel

class ListFragment : Fragment() {
    private lateinit var mViewModel: ListViewModel
    private lateinit var binding: ListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel = getViewModel { ListViewModel() }
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        return binding.root
    }
}