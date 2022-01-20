package com.example.recipefinder

import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
//
//inline fun <reified T: ViewModel> Fragment.getViewModel(crossinline factory: () -> T): T {
//    val viewModelFactory = object : ViewModelProvider.Factory{
//        override fun <U : ViewModel?> create(modelClass: Class<U>): U {
//            return factory() as U
//        }
//
//    }
//
//    return ViewModelProvider(this,viewModelFactory)[T::class.java]
//}
//
//inline fun <reified T: ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T): T {
//    val viewModelFactory = object : ViewModelProvider.Factory{
//        override fun <U : ViewModel?> create(modelClass: Class<U>): U {
//            return factory() as U
//        }
//
//    }
//
//    return ViewModelProvider(this,viewModelFactory)[T::class.java]
//}
//
fun AppCompatImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}
