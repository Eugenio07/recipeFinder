package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.data.repository.RecipeRepository
import com.example.domain.Recipe
import com.example.recipefinder.R
import com.example.recipefinder.data.database.RecipeDataBase
import com.example.recipefinder.data.database.RoomDataSource
import com.example.recipefinder.data.server.theMealDB.TheMealDBDataSource
import com.example.recipefinder.data.toRecipe
import com.example.recipefinder.databinding.DetailFragmentBinding
import com.example.recipefinder.getViewModel
import com.example.recipefinder.loadUrl
import com.example.use.RecipeUseCases
import com.orhanobut.logger.Logger

class DetailFragment : Fragment() {
    private lateinit var mViewModel: DetailViewModel
    private lateinit var binding: DetailFragmentBinding
    private lateinit var recipe: Recipe




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recipe = DetailFragmentArgs.fromBundle(requireArguments()).recipe.toRecipe()
        mViewModel = getViewModel {
            DetailViewModel(
                RecipeUseCases(
                    RecipeRepository(
                        RoomDataSource(RecipeDataBase.getInstance(requireContext())),
                        TheMealDBDataSource()
                    )
                ), recipe
            )
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding.viewModel = mViewModel

        Logger.d("recipe: ${recipe.strMeal}, ${recipe.strInstructions} ")

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
            collapsingToolbar.title = recipe.strMeal
            tvInstructions.text = recipe.strInstructions
            ivRecipe.loadUrl(recipe.strMealThumb!!)
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