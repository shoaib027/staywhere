package com.example.staywhere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.staywhere.ui.theme.StaywhereTheme

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StaywhereTheme {
                StayWhereApp()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun StayWherePreview() {
    StaywhereTheme {
       StayWhereApp()
    }
}