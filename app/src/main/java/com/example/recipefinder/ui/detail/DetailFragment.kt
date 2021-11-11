package com.example.recipefinder.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.recipefinder.R
import com.example.recipefinder.databinding.DetailFragmentBinding
import com.example.recipefinder.loadUrl
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
        changeFavIcon(false)
        mViewModel.isFav.observe(viewLifecycleOwner, Observer(::changeFavIcon))
        mViewModel.findRecipe(mViewModel.recipe.idMeal!!)
        return binding.root
    }

    private fun changeFavIcon(isFav: Boolean){
        val iconFav = if (isFav) R.drawable.ic_favorite else R.drawable.ic_no_favorite
        binding.btnFavorite.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                iconFav
            )
        )
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