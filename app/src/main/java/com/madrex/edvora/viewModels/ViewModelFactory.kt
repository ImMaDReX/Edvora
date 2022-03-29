package com.madrex.edvora.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madrex.edvora.repository.RideRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val map: Map<Class<*>, @JvmSuppressWildcards ViewModel>): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return map[modelClass] as T
    }
}