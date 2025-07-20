package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.Presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.Presentation.viewModel.LocationWithDistance
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.LocationDomain

@Composable
fun CoffeeShopItem(
    locationWithDistance: LocationWithDistance,
    onClick: () -> Unit,
) {
    val location = locationWithDistance.location
    val distance = locationWithDistance.distance


    val distanceText = when {
        distance < 0 -> "Расстояние неизвестно"
        distance > 1000 -> "%.1f км от вас ".format(distance / 1000)
        else -> "%.0f м от вас".format(distance)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(71.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = location.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = distanceText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
                )

            }

        }
    }
}