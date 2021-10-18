package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.Category
import com.example.domain.Ingredient
import com.example.recipefinder.R
import com.example.recipefinder.collapse
import com.example.recipefinder.databinding.CategoryItemBinding
import com.example.recipefinder.databinding.IngredientItemListBinding
import com.example.recipefinder.expand
import com.example.recipefinder.toggleArrow
import java.util.ArrayList

class CategoriesAdapter(categoryList: List<Category>, private val clickListener: CategoryListener) :
    RecyclerView.Adapter<CategoriesAdapter.CountryHolder>() {

    private var items = ArrayList<Category>()

    init {
        this.items = categoryList as ArrayList<Category>
    }

    class CountryHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun render(item: Category, clickListener: CategoryListener) {
            with(binding) {
                textViewDescription.text = item.strCategoryDescription
                categoryName.text = item.strCategory
                Glide.with(binding.root)
                    .load(item.strCategoryThumb)
                    .into(categoryImage)
                btExpand.setOnClickListener {
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
                if(!item.expanded){
                    toggleArrow(false,btExpand)
                    lytExpand.visibility = View.GONE
                }else{
                    toggleArrow(true,btExpand)
                    lytExpand.visibility = View.VISIBLE
                }

                category = item
                this.clickListener = clickListener
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(layoutInflater, parent, false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.render(items[position],clickListener)
    }

    override fun getItemCount(): Int = items.size
}

class CategoryListener(val clickListener: (category: Category) -> Unit) {
    fun onClick(category: Category) = clickListener(category)
}