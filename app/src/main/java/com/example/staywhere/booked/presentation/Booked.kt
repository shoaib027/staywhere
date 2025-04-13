package com.example.staywhere.booked.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Booked(modifier: Modifier = Modifier) {
    Text(
        text = "Book a property to see here",
        modifier = Modifier.padding(4.dp),
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center

    )
}
