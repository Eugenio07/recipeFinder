package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipefinder.databinding.IngredientItemBinding
import com.example.recipefinder.ui.DetailFragment.IngredientItem


class IngredientAdapter(private val itemList: List<IngredientItem>) :
    RecyclerView.Adapter<IngredientAdapter.IngredientHolder>() {


    class IngredientHolder(private val binding: IngredientItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun render(item: IngredientItem) {
            with(binding) {
                if(!item.name.isNullOrEmpty()){
                    ingredientName.text = item.name
                    ingredientMeasure.text = item.measure
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = IngredientItemBinding.inflate(layoutInflater, parent, false)
        return IngredientHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        holder.render(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}