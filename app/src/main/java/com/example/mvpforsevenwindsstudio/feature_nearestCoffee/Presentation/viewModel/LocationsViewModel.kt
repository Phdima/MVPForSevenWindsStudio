package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.Presentation.viewModel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.LocationDomain
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.service.LocationService
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.useCase.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase,
    private val locationService: LocationService,
) : ViewModel() {


    private val _userLocation = MutableStateFlow<Location?>(null)


    private val _locations = MutableStateFlow<List<LocationWithDistance>>(emptyList())
    val locations: StateFlow<List<LocationWithDistance>> = _locations

    init {
        loadLocations()
        getUserLocation()
    }

    private fun getUserLocation() {
        viewModelScope.launch {
            _userLocation.value = locationService.getCurrentLocation()
        }
    }

    private fun loadLocations() {
        viewModelScope.launch {
            val result = locationUseCase()
            if (result.isSuccess) {
                val locations = result.getOrNull() ?: emptyList()
                _locations.value = locations.map { location ->
                    LocationWithDistance(
                        location = location,
                        distance = calculateDistanceToLocation(location)
                    )
                }.sortedBy { it.distance }
            }
        }
    }

    private fun calculateDistanceToLocation(location: LocationDomain): Float {
        val userLocation = _userLocation.value
        return if (userLocation != null) {
            locationService.calculateDistance(
                userLocation.latitude,
                userLocation.longitude,
                location.latitude,
                location.longitude
            )
        } else {
            -1f
        }
    }
}


data class LocationWithDistance(
    val location: LocationDomain,
    val distance: Float,
)