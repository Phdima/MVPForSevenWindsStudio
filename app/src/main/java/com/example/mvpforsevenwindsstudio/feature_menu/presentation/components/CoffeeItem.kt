package com.example.mvpforsevenwindsstudio.feature_menu.presentation.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mvpforsevenwindsstudio.R
import com.example.mvpforsevenwindsstudio.feature_menu.domain.model.MenuItemDomain
import com.example.mvpforsevenwindsstudio.feature_menu.presentation.viewmodel.MenuViewModel
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.Presentation.viewModel.LocationWithDistance

@Composable
fun CoffeeItem(
    item: MenuItemDomain,
    currentQuantity: Int,
    onItemClick: () -> Unit,
    onQuantityUpdate: (Int) -> Unit
) {
    Log.d("CoffeeItem", "Recomposing for ${item.id} with quantity $currentQuantity")

    Card(
        modifier = Modifier
            .height(205.dp)
            .clickable(onClick = onItemClick),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onBackground)
    ) {


        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                if (!item.imageUrl.isNullOrEmpty()) {
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = "фото кофе",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        placeholder = painterResource(R.mipmap.coffeepic2_foreground),
                        error = painterResource(R.mipmap.coffeepic2_foreground)
                    )
                } else {
                    Image(
                        painter = painterResource(R.mipmap.coffeepic2_foreground),
                        contentDescription = "изображение отсутствует",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,

                        )
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(68.dp)
                    .padding(8.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.price.toInt().toString() + " руб",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        IconButton(
                            onClick = {onQuantityUpdate(currentQuantity - 1)
                                Log.d("CoffeeItem", "Decrement: ${item.id}")},
                            modifier = Modifier.size(24.dp)
                        ) {
                            Text(
                                text = "-",
                                color = MaterialTheme.colorScheme.secondary,

                                )
                        }

                        Text(
                            text = currentQuantity.toString(),
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )

                        IconButton(
                            onClick = { onQuantityUpdate(currentQuantity + 1)
                                Log.d("CoffeeItem", "Increment: ${item.id}")},
                            modifier = Modifier.size(24.dp)
                        ) {
                            Text(
                                text = "+",
                                color = MaterialTheme.colorScheme.secondary,

                                )
                        }
                    }
                }

            }

        }


    }
}