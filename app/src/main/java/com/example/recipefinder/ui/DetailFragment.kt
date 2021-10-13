package com.example.recipefinder.ui

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.data.repository.CountriesRepository
import com.example.data.repository.RecipeRepository
import com.example.domain.Recipe
import com.example.recipefinder.PermissionChecker
import com.example.recipefinder.R
import com.example.recipefinder.data.database.db.RecipeDataBase
import com.example.recipefinder.data.database.db.RoomDataSource
import com.example.recipefinder.data.server.restCountries.RestCountryDataSource
import com.example.recipefinder.data.server.theMealDB.TheMealDBDataSource
import com.example.recipefinder.data.toRecipe
import com.example.recipefinder.databinding.DetailFragmentBinding
import com.example.recipefinder.getViewModel
import com.example.recipefinder.loadUrl
import com.example.use.RecipeUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val mViewModel: DetailViewModel by viewModels()
    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding.viewModel = mViewModel

        initializerToolbar()
        updateUI()
        mViewModel.fillIngredientsList()
        binding.rvIngredients.adapter = IngredientAdapter(mViewModel.ingredientsList)

        mViewModel.isFav.observe(viewLifecycleOwner, {
            val iconFav = if (it) R.drawable.ic_favorite else R.drawable.ic_no_favorite
            binding.btnFavorite.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    iconFav
                )
            )
        })

        return binding.root
    }

    private fun updateUI() {
        with(binding) {
            collapsingToolbar.title = viewModel?.recipe?.strMeal
            tvInstructions.text = viewModel?.recipe?.strInstructions
            ivRecipe.loadUrl(viewModel?.recipe?.strMealThumb!!)
        }
    }

    private fun initializerToolbar() {
        with((activity as AppCompatActivity)) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = null
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}