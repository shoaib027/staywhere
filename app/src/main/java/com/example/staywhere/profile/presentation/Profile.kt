package com.example.staywhere.profile.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.staywhere.NavDestination

@Composable
fun Profile(modifier: Modifier = Modifier, navController: NavHostController) {
    SignupScreen(navController = navController)
}