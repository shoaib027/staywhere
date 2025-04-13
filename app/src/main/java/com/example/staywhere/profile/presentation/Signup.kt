package com.example.staywhere.profile.presentation

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.staywhere.NavDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavHostController) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var nameError by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf("") }
    var confirmPasswordError by rememberSaveable { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }

    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validateForm(): Boolean {
        var isValid = true

        if (name.isEmpty()) {
            nameError = "Name is required"
            isValid = false
        }

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

        if (confirmPassword.isEmpty()) {
            confirmPasswordError = "Confirm password is required"
            isValid = false
        } else if (password != confirmPassword) {
            confirmPasswordError = "Passwords don't match"
            isValid = false
        }

        return isValid
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Create an account or login to continue") }
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
                    // Name Field
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        TextField(
                            value = name,
                            onValueChange = {
                                name = it
                                nameError = ""
                            },
                            label = { Text("Name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            isError = nameError.isNotEmpty(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            )
                        )
                        if (nameError.isNotEmpty()) {
                            Text(
                                text = nameError,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                            )
                        }
                    }

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

                    // Confirm Password Field
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        TextField(
                            value = confirmPassword,
                            onValueChange = {
                                confirmPassword = it
                                confirmPasswordError = ""
                            },
                            label = { Text("Confirm Password") },
                            visualTransformation = PasswordVisualTransformation(),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            isError = confirmPasswordError.isNotEmpty(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password
                            )
                        )
                        if (confirmPasswordError.isNotEmpty()) {
                            Text(
                                text = confirmPasswordError,
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
                                // Handle signup logic here
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Sign Up")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Text(
                            text = "Already have an account?"
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Login",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable{
                                // Navigate to login screen
                                navController.navigate(NavDestination.Login)
                            }
                        )

                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
//    SignupScreen(navControl)
}