package com.example.recipefinder

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T: ViewModel> Fragment.getViewModel(crossinline factory: () -> T): T {
    val viewModelFactory = object : ViewModelProvider.Factory{
        override fun <U : ViewModel?> create(modelClass: Class<U>): U {
            return factory() as U
        }

    }

    return ViewModelProvider(this,viewModelFactory)[T::class.java]
}

inline fun <reified T: ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T): T {
    val viewModelFactory = object : ViewModelProvider.Factory{
        override fun <U : ViewModel?> create(modelClass: Class<U>): U {
            return factory() as U
        }

    }

    return ViewModelProvider(this,viewModelFactory)[T::class.java]
}