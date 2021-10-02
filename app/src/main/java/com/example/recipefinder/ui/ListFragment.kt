package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.data.repository.RecipeRepository
import com.example.recipefinder.R
import com.example.recipefinder.data.database.RecipeDataBase
import com.example.recipefinder.data.database.RoomDataSource
import com.example.recipefinder.data.server.theMealDB.TheMealDBDataSource
import com.example.recipefinder.databinding.ListFragmentBinding
import com.example.recipefinder.getViewModel
import com.example.use.RecipeUseCases
import com.orhanobut.logger.Logger

class ListFragment : Fragment() {
    private lateinit var mViewModel: ListViewModel
    private lateinit var binding: ListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel = getViewModel { ListViewModel(
            RecipeUseCases(
                RecipeRepository(
                    RoomDataSource(RecipeDataBase.getInstance(requireContext())),
                    TheMealDBDataSource()
                )
            )
        ) }
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        binding.viewModel = mViewModel

        val recipeList = ListFragmentArgs.fromBundle(requireArguments()).listOfRecipes
        Logger.d("lista de recetas: $recipeList")
        return binding.root
    }
}