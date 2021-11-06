package com.example.recipefinder.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.Recipe
import com.example.recipefinder.databinding.RecipeItemBinding

class RecipeAdapter(private val recipeList: List<Recipe>, private val clickListener: RecipeListener) :
    RecyclerView.Adapter<RecipeAdapter.CountryHolder>() {

    class CountryHolder(private val binding: RecipeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun render(item: Recipe, clickListener: RecipeListener) {
            with(binding) {
                recipeName.text = item.strMeal
                recipe = item
                this.clickListener = clickListener
                Glide.with(binding.root)
                    .load(item.strMealThumb)
                    .into(recipeImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecipeItemBinding.inflate(layoutInflater, parent, false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.render(recipeList[position],clickListener)
    }

    override fun getItemCount(): Int = recipeList.size
}

class RecipeListener(val clickListener: (recipe: Recipe) -> Unit) {
    fun onClick(recipe: Recipe) = clickListener(recipe)
}