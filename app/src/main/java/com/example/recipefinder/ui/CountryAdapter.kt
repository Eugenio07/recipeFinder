package com.example.recipefinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.Country
import com.example.recipefinder.R
import com.example.recipefinder.databinding.CountryItemBinding

class CountryAdapter(private val countryList: List<Country>, private val clickListener: CountryListener) :
    RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    class CountryHolder(private val binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun render(item: Country,clickListener: CountryListener) {
            with(binding) {
                countryName.text = item.strArea
                country = item
                this.clickListener = clickListener
                Glide.with(binding.root)
                    .load(item.flag)
                    .into(countryImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CountryItemBinding.inflate(layoutInflater, parent, false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.render(countryList[position],clickListener)
    }

    override fun getItemCount(): Int = countryList.size
}

class CountryListener(val clickListener: (country: Country) -> Unit) {
    fun onClick(country: Country) = clickListener(country)
}