package com.example.recipefinder.ui.principal

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.domain.Event
import com.example.recipefinder.R
import com.example.recipefinder.RecipeList
import com.example.recipefinder.data.server.theMealDB.NETWORK_STATUS
import com.example.recipefinder.data.toRecipeApp
import com.example.recipefinder.databinding.PrincipalFragmentBinding
import com.example.recipefinder.ui.principal.PrincipalViewModel.*
import com.example.recipefinder.ui.principal.PrincipalViewModel.PrincipalModel.*
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrincipalFragment : Fragment() {
    private val mViewModel: PrincipalViewModel by viewModels()
    private lateinit var binding: PrincipalFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.principal_fragment, container, false)
        binding.viewModel = mViewModel

        mViewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))

        binding.etSearch.setOnEditorActionListener { textView, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                mViewModel.searchedByName(textView.text.toString())
                true
            } else false
        }

        return binding.root
    }

    private fun changedUI(event: Event<PrincipalModel>) {
        event.getContentIfNotHandled()?.let { model ->
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
                        .navigate(
                            PrincipalFragmentDirections.actionPrincipalFragmentToListFragment(
                                list
                            )
                        )
                }

                is GoToSecondary -> this.findNavController().navigate(
                    PrincipalFragmentDirections.actionPrincipalFragmentToSecondaryFragment(
                        model.filter
                    )
                )
                is Network -> {
                    when (model.networkStatus) {
                        NETWORK_STATUS.DONE, NETWORK_STATUS.ERROR -> {
                            binding.progressCircular.visibility = GONE
                        }
                        NETWORK_STATUS.LOADING -> {
                            binding.progressCircular.visibility = VISIBLE
                        }
                    }
                }
                ShowError -> Toast.makeText(
                    context, "The search has returned no results, try beef or honey.",
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }
}