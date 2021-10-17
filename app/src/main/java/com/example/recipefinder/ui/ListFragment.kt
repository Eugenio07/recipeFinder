package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.data.repository.RecipeRepository
import com.example.recipefinder.R
import com.example.recipefinder.data.database.db.RecipeDataBase
import com.example.recipefinder.data.database.db.RoomDataSource
import com.example.recipefinder.data.server.theMealDB.TheMealDBDataSource
import com.example.recipefinder.data.toRecipeApp
import com.example.recipefinder.databinding.ListFragmentBinding
import com.example.recipefinder.getViewModel
import com.example.use.RecipeUseCases
import androidx.lifecycle.Observer
import com.example.domain.Event
import com.example.recipefinder.data.server.theMealDB.NETWORK_STATUS
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

        binding.rvSecondary.adapter = RecipeAdapter(recipeList, RecipeListener {
            mViewModel.recipeClicked(it)
        })

        mViewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))

        return binding.root
    }

    private fun changedUI(event: Event<ListViewModel.ListModel>) {
        event.getContentIfNotHandled()?.let { model ->
            Logger.d("model: $model")
            when (model) {
                is ListViewModel.ListModel.GoToDetail -> {
                    this.findNavController()
                        .navigate(
                            ListFragmentDirections.actionListFragmentToDetailFragment(
                                model.recipe.toRecipeApp()
                            )
                        )
                }
                is ListViewModel.ListModel.Network -> {
                    when(model.networkStatus){
                        NETWORK_STATUS.DONE, NETWORK_STATUS.ERROR -> {
                            binding.progressCircular.visibility = View.GONE
                        }
                        NETWORK_STATUS.LOADING -> {
                            binding.progressCircular.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}