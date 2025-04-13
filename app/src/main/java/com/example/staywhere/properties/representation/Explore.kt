package com.example.staywhere.properties.representation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

data class PropertyItems(
    val image: String,
    val title: String,
    val location: String,
    val rating: String,
    val distance: String,
    val price: String
)

@Composable
fun Explore(navController: NavHostController) {
    val properties = listOf(
        PropertyItems(
            image = "https://cdn.pixabay.com/photo/2018/01/26/08/37/architecture-3108075_1280.jpg",
            title = "Manali, India",
            location = "Manali, Himachal Pradesh",
            rating = "4.5",
            distance = "293 meters away",
            price = "₹3600"
        )
    )
    LazyColumn {
        item{
            properties.forEach { property ->
                PropertyCard(navController = navController, image = property.image, title = property.title, location = property.location, rating = property.rating, distance = property.distance, price = property.price )
            }
        }
    }


//    LazyColumn {
//        items(5) {
//            Column(
//                modifier = Modifier.padding(15.dp)
//            ) {
//                Box() {
//                    Image(
//                        painter = painterResource(R.drawable.residence),
//                        contentDescription = "residence",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(450.dp)
//                            .border(2.dp, Color.Transparent, shape = RoundedCornerShape(12.dp))
//                            .clip(RoundedCornerShape(12.dp))
//                            .clickable {
////                                    navController.navigate("explore/propertyDetails")
//                            },
//                        contentScale = ContentScale.Crop,
//                    )
//                    IconButton(
//                        onClick = {},
//                        modifier = Modifier
//                            .padding(5.dp)
//                            .align(Alignment.TopEnd)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Outlined.FavoriteBorder,
//                            contentDescription = "Favorite Icon",
//                            modifier = Modifier
//                                .size(30.dp)
//
//                        )
//                    }
//
//
//                }
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "Manali, India",
//                        fontSize = 16.sp,
//                        color = Color.Black,
//                        modifier = Modifier.padding(4.dp)
//                    )
//                    Row {
//                        Text(text = "4.5")
//                        Icon(
//                            imageVector = Icons.Filled.Star,
//                            contentDescription = "Star"
//                        )
//                    }
//
//                }
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.Top
//                ) {
//                    Text(
//                        text = "293 meters away",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.W400,
//                        color = Color.Gray,
//                        modifier = Modifier.padding(start = 4.dp)
//                    )
//
//                }
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.Top
//                ) {
//                    Text(
//                        text = "18-23 Mar",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.W400,
//                        color = Color.Gray,
//                        modifier = Modifier.padding(start = 4.dp)
//                    )
//
//                }
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = "₹3600",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.W600,
//                        color = Color.Black,
//                        modifier = Modifier.padding(start = 4.dp)
//                    )
//
//                }
//
//            }
//
//        }
//    }
}









