package com.example.recipefinder.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.Ingredient
import com.example.domain.Recipe
import com.example.recipefinder.collapse
import com.example.recipefinder.databinding.IngredientItemListBinding
import com.example.recipefinder.expand
import com.example.recipefinder.toggleArrow
import java.util.ArrayList

class IngredientListAdapter(ingredientList: List<Ingredient>, private val clickListener: IngredientListener) :
    RecyclerView.Adapter<IngredientListAdapter.CountryHolder>() {

    private var items = ArrayList<Ingredient>()

    init {
        this.items = ingredientList as ArrayList<Ingredient>
    }

    class CountryHolder(private val binding: IngredientItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun render(item: Ingredient, clickListener: IngredientListener) {
            with(binding) {
                if(!item.strDescription.isNullOrEmpty()){
                    textViewDescription.text = item.strDescription
                }
                name.text = item.strIngredient
                btExpand.setOnClickListener {
                    if(item.strDescription.isNullOrEmpty()){
                        Toast.makeText(it.context,"Description not available",Toast.LENGTH_SHORT).show()
                    }else{
                        if(item.expanded){
                            toggleArrow(false,it)
                            collapse(lytExpand)
                            item.expanded = false
                        }else{
                            toggleArrow(true,it)
                            expand(lytExpand)
                            item.expanded = true
                        }
                    }
                }
                if(!item.expanded){
                    toggleArrow(false,btExpand)
                    lytExpand.visibility = View.GONE
                }else{
                    toggleArrow(true,btExpand)
                    lytExpand.visibility = View.VISIBLE
                }

                ingredient = item
                this.clickListener = clickListener
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = IngredientItemListBinding.inflate(layoutInflater, parent, false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.render(items[position],clickListener)
    }

    override fun getItemCount(): Int = items.size
}

class IngredientListener(val clickListener: (ingredient: Ingredient) -> Unit) {
    fun onClick(ingredient: Ingredient) = clickListener(ingredient)
}
