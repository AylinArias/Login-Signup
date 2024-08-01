package com.example.login_signup.screens

import android.annotation.SuppressLint
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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_signup.composables.WaveHeader
import com.example.login_signup.viewmodels.LoginViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val loginViewModel: LoginViewModel = getViewModel()

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        loginViewModel.rememberMeState.value =
            loginViewModel.preferencesManager.loadRememberMePreference()
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
                        text = "Ingreso",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        color = MaterialTheme.colors.onBackground
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
                        color = MaterialTheme.colors.onBackground
                    )

                    TextField(
                        value = loginViewModel.emailState.value,
                        onValueChange = { loginViewModel.emailState.value = it },
                        placeholder = { Text("Ingresar correo electrónico") },
                        isError = loginViewModel.emailError.value.isNotEmpty(),
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

                    if (loginViewModel.emailError.value.isNotEmpty()) {
                        Text(
                            text = loginViewModel.emailError.value,
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
                        color = MaterialTheme.colors.onBackground
                    )

                    TextField(
                        value = loginViewModel.passwordState.value,
                        onValueChange = { loginViewModel.passwordState.value = it },
                        placeholder = { Text("Ej: abcABC#123") },
                        trailingIcon = {
                            IconButton(onClick = {
                                loginViewModel.passwordVisible.value =
                                    !loginViewModel.passwordVisible.value
                            }) {
                                Icon(
                                    imageVector = if (loginViewModel.passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (loginViewModel.passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = loginViewModel.passwordError.value.isNotEmpty(),
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

                    if (loginViewModel.passwordError.value.isNotEmpty()) {
                        Text(
                            text = loginViewModel.passwordError.value,
                            color = MaterialTheme.colors.error,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = loginViewModel.rememberMeState.value,
                            onCheckedChange = {
                                loginViewModel.toggleRememberMe()
                                val message = if (loginViewModel.rememberMeState.value) {
                                    "Recordarme activado"
                                } else {
                                    "Recordarme desactivado"
                                }
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = message,
                                        duration = SnackbarDuration.Long
                                    )
                                }
                            },
                            colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
                        )
                        Text(
                            text = "Recordarme",
                            color = MaterialTheme.colors.onBackground
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        TextButton(
                            onClick = {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Recuperar contraseña aún no implementado",
                                        duration = SnackbarDuration.Long
                                    )
                                }
                            }
                        ) {
                            Text(
                                text = "¿Contraseña olvidada?",
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            loginViewModel.validateFields()
                            if (loginViewModel.emailError.value.isEmpty() && loginViewModel.passwordError.value.isEmpty()) {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Ingreso exitoso",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                                navController.navigate("home")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
                    ) {
                        Text(
                            text = "Ingresar",
                            color = MaterialTheme.colors.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "¿No tienes una cuenta?",
                            color = MaterialTheme.colors.onBackground
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        TextButton(
                            onClick = {
                                navController.navigate("signup")
                            }
                        ) {
                            Text(
                                text = "Registrarse",
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
fun LoginScreenPreview() {
    LoginSignupTheme {
        val navController = rememberNavController()
        LoginScreen(navController)
    }
}*/