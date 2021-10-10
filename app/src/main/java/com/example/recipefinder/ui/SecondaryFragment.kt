package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.data.repository.RecipeRepository
import com.example.recipefinder.R
import com.example.recipefinder.data.database.db.RecipeDataBase
import com.example.recipefinder.data.database.db.RoomDataSource
import com.example.recipefinder.data.server.theMealDB.TheMealDBDataSource
import com.example.recipefinder.databinding.SecondaryFragmentBinding
import com.example.recipefinder.getViewModel
import com.example.use.RecipeUseCases
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.recipefinder.RecipeList
import com.example.recipefinder.ui.SecondaryViewModel.SecondaryModel
import com.example.recipefinder.ui.SecondaryViewModel.SecondaryModel.*
import com.orhanobut.logger.Logger

class SecondaryFragment : Fragment() {
    private lateinit var mViewModel: SecondaryViewModel
    private lateinit var binding: SecondaryFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel = getViewModel {
            SecondaryViewModel(
                RecipeUseCases(
                    RecipeRepository(
                        RoomDataSource(RecipeDataBase.getInstance(requireContext())),
                        TheMealDBDataSource()
                    )
                )
            )
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.secondary_fragment, container, false)
        binding.viewModel = mViewModel

        val filterType = SecondaryFragmentArgs.fromBundle(requireArguments()).filterType
        mViewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))
        mViewModel.getListOfFilters(filterType)

        return binding.root
    }

    private fun changedUI(model: SecondaryModel) {
        when (model) {
            is AreaList -> {
                Logger.d("Countries: ${model.countries}")
                mViewModel.filterByArea(model.countries[0].demonym!!)
            }
            is CategoryList -> {
                Logger.d("Categories: ${model.categories}")
                mViewModel.filterByCategory(model.categories[0].strCategory!!)
            }
            is IngredientList -> {
                Logger.d("Ingredient: ${model.ingredients}")
                mViewModel.filterByIngredient(model.ingredients[0].strIngredient!!)
            }
            is FilteredRecipeList -> {
                val list = RecipeList()
                list.addAll(model.listOfRecipes)
                this.findNavController()
                    .navigate(SecondaryFragmentDirections.actionSecondaryFragmentToListFragment(list))
            }
        }
    }
}