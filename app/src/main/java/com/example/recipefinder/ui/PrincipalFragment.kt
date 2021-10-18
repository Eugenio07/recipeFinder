package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.data.repository.RecipeRepository
import com.example.domain.Event
import com.example.recipefinder.R
import com.example.recipefinder.RecipeList
import com.example.recipefinder.data.database.db.RecipeDataBase
import com.example.recipefinder.data.database.db.RoomDataSource
import com.example.recipefinder.data.server.theMealDB.TheMealDBDataSource
import com.example.recipefinder.data.toRecipeApp
import com.example.recipefinder.databinding.PrincipalFragmentBinding
import com.example.recipefinder.getViewModel
import com.example.recipefinder.ui.PrincipalViewModel.*
import com.example.recipefinder.ui.PrincipalViewModel.PrincipalModel.*
import com.example.use.RecipeUseCases
import com.orhanobut.logger.Logger

class PrincipalFragment : Fragment() {
    private lateinit var mViewModel: PrincipalViewModel
    private lateinit var binding: PrincipalFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel = getViewModel {
            PrincipalViewModel(
                RecipeUseCases(
                    RecipeRepository(
                        RoomDataSource(RecipeDataBase.getInstance(requireContext())),
                        TheMealDBDataSource()
                    )
                )
            )
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.principal_fragment, container, false)
        binding.viewModel = mViewModel

        mViewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))

        binding.etSearch.setOnEditorActionListener { textView, i, _ ->
            Logger.d("text = ${textView.text}")
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                Logger.d("entro")
                mViewModel.searchedByName(textView.text.toString())
                true
            } else false
        }

        return binding.root
    }

    private fun changedUI(event: Event<PrincipalModel>) {
        event.getContentIfNotHandled()?.let { model ->
            Logger.d("model: $model")
            when (model) {
                is GoToDetail -> {
                    this.findNavController()
                        .navigate(
                            PrincipalFragmentDirections.actionPrincipalFragmentToDetailFragment(
                                model.recipe.toRecipeApp()
                            )
                        )
                }
                is GoToList -> {
                    val list = RecipeList()
                    list.addAll(model.listOfRecipes)
                    this.findNavController()
                        .navigate(PrincipalFragmentDirections.actionPrincipalFragmentToListFragment(list))
                }

                is GoToSecondary -> this.findNavController().navigate(
                    PrincipalFragmentDirections.actionPrincipalFragmentToSecondaryFragment(model.filter)
                )
            }
        }
    }
}