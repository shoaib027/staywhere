package com.example.staywhere.favourite.presentation


import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun Favourite() {
    Text(
        text = "You don't have any favourites now",
        modifier = Modifier.padding(4.dp),
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center

    )
}