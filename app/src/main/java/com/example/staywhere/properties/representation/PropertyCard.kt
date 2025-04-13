package com.example.staywhere.properties.representation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.staywhere.NavDestination
import com.example.staywhere.R


@Composable
fun PropertyCard(modifier: Modifier = Modifier, navController: NavHostController, image: String, title: String, location: String, rating: String, distance: String, price: String) {
    var isLiked by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(15.dp)
    ) {
        Box() {
            Image(
                painter = painterResource(R.drawable.residence),
                contentDescription = "residence",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .border(2.dp, Color.Transparent, shape = RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        navController.navigate(
                            NavDestination.PropertyDetails(
                                images = listOf(
                                    "https://cdn.pixabay.com/photo/2018/01/26/08/37/architecture-3108075_1280.jpg",
                                    "https://cdn.pixabay.com/photo/2024/01/19/11/08/building-8518703_1280.jpg",
                                    "https://cdn.pixabay.com/photo/2023/11/06/02/18/living-room-8368639_1280.jpg"
                                )
                            )
                        )
                    },
                contentScale = ContentScale.Crop,
            )
            IconButton(
                onClick = {
                    isLiked = !isLiked
                },
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    tint = if(isLiked) Color.Red else Color.Gray,
                    modifier = Modifier
                        .size(30.dp)

                )
            }


        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "New Delhi, India",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(4.dp)
            )
            Row {
                Text(text = "4.5")
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star"
                )
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "293 meters away",
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = Color.Gray,
                modifier = Modifier.padding(start = 4.dp)
            )

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Listed on 18 March",
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = Color.Gray,
                modifier = Modifier.padding(start = 4.dp)
            )

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "₹1250000.0",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black,
                modifier = Modifier.padding(start = 4.dp)
            )

        }

    }

}