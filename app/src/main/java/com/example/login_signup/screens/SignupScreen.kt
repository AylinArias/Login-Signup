package com.example.login_signup.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
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
import com.example.login_signup.viewmodels.SignupViewModel
import org.koin.androidx.compose.getViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignupScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val signupViewModel: SignupViewModel = getViewModel()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (signupViewModel.emailErrorState.value != null ||
            signupViewModel.phoneErrorState.value != null ||
            signupViewModel.passwordErrorState.value != null ||
            signupViewModel.confirmPasswordErrorState.value != null
        ) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Por favor corrige los errores",
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
                        value = signupViewModel.emailState.value,
                        onValueChange = { signupViewModel.emailState.value = it },
                        placeholder = { Text("Ingresar correo electrónico") },
                        isError = signupViewModel.emailErrorState.value != null,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.onSurface
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (signupViewModel.emailErrorState.value != null) {
                        Text(
                            text = signupViewModel.emailErrorState.value!!,
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
                        value = signupViewModel.phoneState.value,
                        onValueChange = { signupViewModel.phoneState.value = it },
                        placeholder = { Text("Ej: +54 12345678") },
                        isError = signupViewModel.phoneErrorState.value != null,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.onSurface
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Phone,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (signupViewModel.phoneErrorState.value != null) {
                        Text(
                            text = signupViewModel.phoneErrorState.value!!,
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
                        value = signupViewModel.passwordState.value,
                        onValueChange = { signupViewModel.passwordState.value = it },
                        placeholder = { Text("Ej: abcABC#123") },
                        trailingIcon = {
                            IconButton(onClick = {
                                signupViewModel.togglePasswordVisibility()
                            }) {
                                Icon(
                                    imageVector = if (signupViewModel.passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (signupViewModel.passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = signupViewModel.passwordErrorState.value != null,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.onSurface
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (signupViewModel.passwordErrorState.value != null) {
                        Text(
                            text = signupViewModel.passwordErrorState.value!!,
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
                        value = signupViewModel.confirmPasswordState.value,
                        onValueChange = { signupViewModel.confirmPasswordState.value = it },
                        placeholder = { Text("Reingresar contraseña") },
                        trailingIcon = {
                            IconButton(onClick = {
                                signupViewModel.togglePasswordVisibility()
                            }) {
                                Icon(
                                    imageVector = if (signupViewModel.passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (signupViewModel.passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = signupViewModel.confirmPasswordErrorState.value != null,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            focusedIndicatorColor = MaterialTheme.colors.primary,
                            unfocusedIndicatorColor = MaterialTheme.colors.onSurface
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (signupViewModel.confirmPasswordErrorState.value != null) {
                        Text(
                            text = signupViewModel.confirmPasswordErrorState.value!!,
                            color = MaterialTheme.colors.error,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (signupViewModel.validateFields()) {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Se presionó el botón de crear cuenta",
                                        duration = SnackbarDuration.Long
                                    )
                                }
                                navController.navigate("login")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                    ) {
                        Text(
                            text = "Crear cuenta",
                            color = MaterialTheme.colors.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "¿Ya tienes una cuenta?",
                            color = MaterialTheme.colors.onBackground
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        TextButton(onClick = {
                            navController.navigate("login")
                        }) {
                            Text(
                                text = "Ingresar",
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
            }
        }
    )
}


/*
@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    LoginSignupTheme {
        val navController = rememberNavController()
        SignupScreen(navController)
    }
}*/