package com.example.staywhere.upload.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

data class Property(
    val description: String,
    val address: Address,
    val price: Double,
    val bedrooms: Int,
    val bathrooms: Int,
    val area: Double,
    val imageUrl: String
)

data class Address(
    val houseno: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyFormScreen(
) {
    var description by remember { mutableStateOf("") }
    var houseNo by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var zip by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var bedrooms by remember { mutableStateOf("") }
    var bathrooms by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") })
            OutlinedTextField(
                value = houseNo,
                onValueChange = { houseNo = it },
                label = { Text("House No*") })
            OutlinedTextField(
                value = street,
                onValueChange = { street = it },
                label = { Text("Street*") })
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("City*") })
            OutlinedTextField(
                value = state,
                onValueChange = { state = it },
                label = { Text("State*") })
            OutlinedTextField(value = zip, onValueChange = { zip = it }, label = { Text("ZIP") })
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price*") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = bedrooms,
                onValueChange = { bedrooms = it },
                label = { Text("Bedrooms*") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = bathrooms,
                onValueChange = { bathrooms = it },
                label = { Text("Bathrooms*") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = area,
                onValueChange = { area = it },
                label = { Text("Area (sq ft)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )


            ImagePickerScreen()

            Button(
                onClick = {
                    // Build Property and pass it to onSubmit
                    val property = Property(
                        description = description,
                        address = Address(
                            houseno = houseNo,
                            street = street,
                            city = city,
                            state = state,
                            zip = zip
                        ),
                        price = price.toDoubleOrNull() ?: 0.0,
                        bedrooms = bedrooms.toIntOrNull() ?: 0,
                        bathrooms = bathrooms.toIntOrNull() ?: 0,
                        area = area.toDoubleOrNull() ?: 0.0,
                        imageUrl = imageUrl
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }
        }

}


@Composable
fun ImagePickerScreen() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }


    Button(onClick = { launcher.launch("image/*") }) {
        Text("Pick an Image")
    }

    Spacer(modifier = Modifier.height(16.dp))
    imageUri?.let { uri ->
        Image(
            painter = rememberAsyncImagePainter(uri),
            contentDescription = "Selected Image",
            modifier = Modifier.size(200.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadPropScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Upload your property",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                }
            )
        }
    ) { innerPadding ->
        // Pass the padding to ImagePickerScreen if needed
        Box(modifier = modifier.padding(innerPadding)) {
            LazyColumn {
                item {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PropertyFormScreen()
                    }
                }

            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun UploadPropScreenPreview() {
    UploadPropScreen()
}