package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.recipefinder.R
import com.example.recipefinder.databinding.PrincipalFragmentBinding
import com.example.recipefinder.getViewModel

class PrincipalFragment : Fragment() {
    private lateinit var mViewModel: PrincipalViewModel
    private lateinit var binding: PrincipalFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel = getViewModel { PrincipalViewModel() }
        binding = DataBindingUtil.inflate(inflater, R.layout.principal_fragment, container, false)
        return binding.root
    }
}