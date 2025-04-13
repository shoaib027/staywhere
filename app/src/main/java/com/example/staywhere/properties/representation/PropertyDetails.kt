package com.example.staywhere.properties.representation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
data class Property(
    val description: String,
    val address: Address,
    val price: Double,
    val bedrooms: Int,
    val bathrooms: Int,
    val area: Double,
    val imageUrl: String // Optional if you're using multiple images separately
)

data class Address(
    val houseno: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)

@Composable
fun PropertyDetailsScreen(modifier: Modifier = Modifier, images: List<String>,property: Property) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    LazyColumn {
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Image Pager
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) { page ->
                    AsyncImage(
                        model = images[page],
                        contentDescription = "Image $page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Page Indicators (Dots)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    images.forEachIndexed { index, _ ->
                        IndicatorDot(
                            isSelected = pagerState.currentPage == index
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = property.description,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = "Price: ₹${property.price}",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Bedrooms: ${property.bedrooms}",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Bathrooms: ${property.bathrooms}",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Area: ${property.area} sq ft",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(4.dp)
                )

                Text(
                    text = "Address:\n${property.address.houseno}, ${property.address.street},\n" +
                            "${property.address.city}, ${property.address.state} ${property.address.zip}",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(4.dp)
                )
                Button(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Book")
                }

            }

        }
    }


}


@Composable
fun IndicatorDot(
    isSelected: Boolean,
    size: Dp = 10.dp
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(if (isSelected) size * 1.2f else size) // Bigger size for selected dot
            .clip(CircleShape)
            .background(if (isSelected) Color.Black else Color.Gray.copy(alpha = 0.5f))
    )
}

@Preview(showBackground = true)
@Composable
fun PropertyDetailsScreenPreview() {
    val images = listOf(
        "https://source.unsplash.com/random/800x600?nature",
        "https://source.unsplash.com/random/800x600?city",
        "https://source.unsplash.com/random/800x600?beach"
    )
    val property = Property(
        description = "Beautiful House at Zakir Nagar with modern amenities.",
        address = Address(
            houseno = "B/29",
            street = "Street no. 6",
            city = "New Delhi",
            state = "Delhi",
            zip = "110025"
        ),
        price = 1250000.0,
        bedrooms = 4,
        bathrooms = 3,
        area = 2800.0,
        imageUrl = images.first()
    )
    Scaffold() { innerPadding ->
        PropertyDetailsScreen(modifier = Modifier.padding(innerPadding), images = images, property = property)
    }
}