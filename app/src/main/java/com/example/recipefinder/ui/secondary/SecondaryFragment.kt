package com.example.recipefinder.ui.secondary

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recipefinder.R
import com.example.recipefinder.databinding.SecondaryFragmentBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.domain.Event
import com.example.recipefinder.RecipeList
import com.example.recipefinder.data.server.theMealDB.NETWORK_STATUS
import com.example.recipefinder.ui.secondary.SecondaryViewModel.SecondaryModel
import com.example.recipefinder.ui.secondary.SecondaryViewModel.SecondaryModel.*
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondaryFragment : Fragment() {
    private val mViewModel: SecondaryViewModel by viewModels()
    private lateinit var binding: SecondaryFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.secondary_fragment, container, false)
        binding.viewModel = mViewModel

        mViewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getListOfFilters(
            SecondaryFragmentArgs.fromBundle(
                requireArguments()
            ).filterType)
    }

    private fun changedUI(event: Event<SecondaryModel>) {
        event.getContentIfNotHandled()?.let { model ->
            when (model) {
                is AreaList -> {
                    Logger.d("Countries: ${model.countries}")
                    binding.textView.text = getString(R.string.countries)
                    binding.btnMyCountry.text = getString(R.string.find_my_country_recipes, model.country.demonym)
                    binding.btnMyCountry.visibility = VISIBLE
                    Glide.with(binding.root)
                        .load(model.country.flag)
                        .into(binding.countryImage1)
                    Glide.with(binding.root)
                        .load(model.country.flag)
                        .into(binding.countryImage2)
                    binding.countryImage1.visibility = VISIBLE
                    binding.countryImage2.visibility = VISIBLE
                    binding.rvSecondary.adapter = CountryAdapter(model.countries, CountryListener { country ->
                        country.demonym?.let { mViewModel.filterByArea(it) }
                    })
                }
                is CategoryList -> {
                    Logger.d("Categories: ${model.categories}")
                    binding.textView.text = getString(R.string.category)
                    binding.rvSecondary.adapter = CategoriesAdapter(model.categories,
                        CategoryListener {
                        it.strCategory?.let { it1 -> mViewModel.filterByCategory(it1) }
                    })
                   // mViewModel.filterByCategory(model.categories[0].strCategory!!)
                }
                is IngredientList -> {
                    binding.textView.text = getString(R.string.ingredient)
                    binding.rvSecondary.adapter = IngredientListAdapter(model.ingredients,
                        IngredientListener {
                        it.strIngredient?.let { it1 -> mViewModel.filterByIngredient(it1) }
                    })
                }
                is FilteredRecipeList -> {
                    val list = RecipeList()
                    list.addAll(model.listOfRecipes)
                    this.findNavController()
                        .navigate(
                            SecondaryFragmentDirections.actionSecondaryFragmentToListFragment(
                                list
                            )
                        )
                }
                is Network -> {
                    when(model.networkStatus){
                        NETWORK_STATUS.DONE, NETWORK_STATUS.ERROR -> {
                            binding.progressCircular.visibility = GONE
                        }
                        NETWORK_STATUS.LOADING -> {
                            binding.progressCircular.visibility = VISIBLE
                        }
                    }
                }
            }
        }
    }
}