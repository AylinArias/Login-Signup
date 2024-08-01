package com.example.login_signup

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login_signup.composables.WaveHeader
import com.example.login_signup.ui.theme.LoginSignupTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignupScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val emailState = remember { mutableStateOf("") }
    val phoneState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val confirmPasswordState = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val isError = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }


    LaunchedEffect(isError.value) {
        if (isError.value) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Datos ingresados incorrectos",
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
            ) {
                WaveHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "Registro",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.Monospace
                    )

                    Divider(
                        color = MaterialTheme.colors.primary,
                        thickness = 2.dp,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .widthIn(max = 70.dp)
                    )

                    Text(
                        text = "Correo electrónico",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.Monospace
                    )

                    TextField(
                        value = emailState.value,
                        onValueChange = { emailState.value = it },
                        placeholder = { Text("Ingresar correo electrónico") },
                        isError = isError.value,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.onSurface
                        ),
                        leadingIcon = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Email,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Divider(
                                    color = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .height(24.dp)
                                        .width(2.dp)
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    if (isError.value) {
                        Text(
                            text = "Correo electrónico incorrecto",
                            color = MaterialTheme.colors.error,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Número de teléfono",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.Monospace
                    )

                    TextField(
                        value = phoneState.value,
                        onValueChange = { phoneState.value = it },
                        placeholder = { Text("Ej: +54 12345678") },
                        isError = isError.value,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.onSurface
                        ),
                        leadingIcon = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Phone,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Divider(
                                    color = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .height(24.dp)
                                        .width(2.dp)
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    if (isError.value) {
                        Text(
                            text = "Número de teléfono incorrecto",
                            color = MaterialTheme.colors.error,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Contraseña",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.Monospace
                    )

                    TextField(
                        value = passwordState.value,
                        onValueChange = { passwordState.value = it },
                        placeholder = { Text("Ej: abcABC#123") },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisible.value = !passwordVisible.value
                            }) {
                                Icon(
                                    imageVector = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = isError.value,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.onSurface
                        ),
                        leadingIcon = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Lock,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Divider(
                                    color = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .height(24.dp)
                                        .width(2.dp)
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    if (isError.value) {
                        Text(
                            text = "Contraseña incorrecta",
                            color = MaterialTheme.colors.error,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Confirmar contraseña",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.Monospace
                    )

                    TextField(
                        value = confirmPasswordState.value,
                        onValueChange = { confirmPasswordState.value = it },
                        placeholder = { Text("Reingresar contraseña") },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisible.value = !passwordVisible.value
                            }) {
                                Icon(
                                    imageVector = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = isError.value,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.onSurface
                        ),
                        leadingIcon = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Lock,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Divider(
                                    color = MaterialTheme.colors.primary,
                                    modifier = Modifier
                                        .height(24.dp)
                                        .width(2.dp)
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    if (isError.value) {
                        Text(
                            text = "Contraseña incorrecta",
                            color = MaterialTheme.colors.error,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val emailPattern = android.util.Patterns.EMAIL_ADDRESS
                            val phonePattern = Regex("\\+\\d{1,3} \\d{6,15}")

                            Log.d("SignupScreen", "Email: ${emailState.value}")
                            Log.d("SignupScreen", "Phone: ${phoneState.value}")
                            Log.d("SignupScreen", "Password: ${passwordState.value}")
                            Log.d("SignupScreen", "Confirm Password: ${confirmPasswordState.value}")

                            when {
                                emailState.value.isEmpty() || !emailPattern.matcher(emailState.value).matches() -> {
                                    isError.value = true
                                    errorMessage.value = "Correo electrónico incorrecto"
                                }
                                phoneState.value.isEmpty() || !phonePattern.matches(phoneState.value) -> {
                                    isError.value = true
                                    errorMessage.value = "Número de teléfono incorrecto"
                                }
                                passwordState.value.isEmpty() -> {
                                    isError.value = true
                                    errorMessage.value = "Contraseña vacía"
                                }
                                passwordState.value != confirmPasswordState.value -> {
                                    isError.value = true
                                    errorMessage.value = "Las contraseñas no coinciden"
                                }
                                else -> {
                                    isError.value = false
                                    navController.navigate("login")
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                    ) {
                        Text(
                            text = "Crear cuenta",
                            color = MaterialTheme.colors.onPrimary,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    LaunchedEffect(isError.value) {
                        if (isError.value) {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = errorMessage.value,
                                duration = SnackbarDuration.Short
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "¿Ya tienes una cuenta?",
                            color = MaterialTheme.colors.onBackground,
                            fontFamily = FontFamily.Monospace
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        TextButton(
                            onClick = {
                                navController.navigate("login")
                            }
                        ) {
                            Text(
                                text = "Ingresar",
                                color = MaterialTheme.colors.primary,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    LoginSignupTheme {
        val navController = rememberNavController()
        SignupScreen(navController)
    }
}