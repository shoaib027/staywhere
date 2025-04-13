package com.example.staywhere.profile.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.staywhere.NavDestination
import com.example.staywhere.profile.data.DataStoreManager


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var emailError by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }

    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validateForm(): Boolean {
        var isValid = true

        if (email.isEmpty()) {
            emailError = "Email is required"
            isValid = false
        } else if (!validateEmail(email)) {
            emailError = "Invalid email format"
            isValid = false
        }

        if (password.isEmpty()) {
            passwordError = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            passwordError = "Password must be at least 6 characters"
            isValid = false
        }

        return isValid
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Login to continue") }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {

                    // Email Field
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        TextField(
                            value = email,
                            onValueChange = {
                                email = it
                                emailError = ""
                            },
                            label = { Text("Email") },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            isError = emailError.isNotEmpty(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Email
                            )
                        )
                        if (emailError.isNotEmpty()) {
                            Text(
                                text = emailError,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                            )
                        }
                    }

                    // Password Field
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        TextField(
                            value = password,
                            onValueChange = {
                                password = it
                                passwordError = ""
                            },
                            label = { Text("Password") },
                            visualTransformation = PasswordVisualTransformation(),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            isError = passwordError.isNotEmpty(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password
                            )
                        )
                        if (passwordError.isNotEmpty()) {
                            Text(
                                text = passwordError,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (validateForm()) {
                                // Handle login logic here
                                navController.navigate(NavDestination.Explore)
//                                LaunchedEffect(Unit) {
//                                    // Simulate a login response
//                                    val token = "example_token_from_backend"
//
//                                    dataStoreManager.saveAuthToken(token)
//
//                                    // Optionally navigate to main app screen
//                                    navController.navigate(NavDestination.Explore)
//                                }

                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Login")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row {
                        Text("Don't have an account?")
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Sign up",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                                // Navigate to Signup screen
                                navController.navigate(NavDestination.Signup)
                            }
                        )
                    }
                }
            }
        }
    }
}
