package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.Presentation.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.Location
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.useCase.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase,
) : ViewModel() {

    init {
        loadLocations()
    }

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations: StateFlow<List<Location>> = _locations

    private fun loadLocations() {
        viewModelScope.launch {
            try {
                Log.d("LocationsViewModel", "Loading locations started")

                val result = locationUseCase()

                if (result.isSuccess) {
                    val data = result.getOrNull() ?: emptyList()
                    Log.d("LocationsViewModel", "Received ${data.size} locations")

                    data.take(3).forEachIndexed { index, location ->
                        Log.d("LocationsViewModel", "Location $index: ${location.name}")
                    }

                    _locations.value = data
                } else {
                    val exception = result.exceptionOrNull()
                    Log.e("LocationsViewModel", "Error loading locations", exception)
                }
            } catch (e: Exception) {
                Log.e("LocationsViewModel", "Exception in loadLocations", e)
            } finally {
                Log.d("LocationsViewModel", "Loading locations completed")
            }
        }
    }
}