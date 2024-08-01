package com.example.login_signup.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.login_signup.composables.WaveHeader
import com.example.login_signup.ui.theme.LoginSignupTheme
import kotlinx.coroutines.launch



fun provideEncryptedSharedPreferences(context: Context): SharedPreferences {
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    return EncryptedSharedPreferences.create(
        "secret_shared_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}


fun storeRememberMePreference(context: Context, rememberMe: Boolean) {
    val sharedPreferences = provideEncryptedSharedPreferences(context)
    with(sharedPreferences.edit()) {
        putBoolean("remember_me", rememberMe)
        apply()
    }
}


fun loadRememberMePreference(context: Context): Boolean {
    val sharedPreferences = provideEncryptedSharedPreferences(context)
    return sharedPreferences.getBoolean("remember_me", false)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val emailError = remember { mutableStateOf("") }
    val passwordError = remember { mutableStateOf("") }
    val rememberMeState = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        rememberMeState.value = loadRememberMePreference(context)
    }

    fun validateFields() {
        emailError.value = if (emailState.value.isEmpty()) {
            "Correo electrónico requerido"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()) {
            "Correo electrónico inválido"
        } else {
            ""
        }

        passwordError.value = if (passwordState.value.isEmpty()) {
            "Contraseña requerida"
        } else if (passwordState.value.length < 8) {
            "La contraseña debe tener al menos 8 caracteres"
        } else {
            ""
        }
    }

    LaunchedEffect(emailError.value, passwordError.value) {
        if (emailError.value.isNotEmpty() || passwordError.value.isNotEmpty()) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Corrige los errores antes de continuar",
                duration = SnackbarDuration.Long
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
                        text = "Ingreso",
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
                        color = MaterialTheme.colors.onBackground
                    )

                    TextField(
                        value = emailState.value,
                        onValueChange = { emailState.value = it },
                        placeholder = { Text("Ingresar correo electrónico") },
                        isError = emailError.value.isNotEmpty(),
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

                    if (emailError.value.isNotEmpty()) {
                        Text(
                            text = emailError.value,
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
                        value = passwordState.value,
                        onValueChange = { passwordState.value = it },
                        placeholder = { Text("Ej: abcABC#123") },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                                Icon(
                                    imageVector = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = passwordError.value.isNotEmpty(),
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

                    if (passwordError.value.isNotEmpty()) {
                        Text(
                            text = passwordError.value,
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
                            checked = rememberMeState.value,
                            onCheckedChange = { isChecked ->
                                rememberMeState.value = isChecked
                                storeRememberMePreference(context, isChecked)

                                val message = if (isChecked) {
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
                            validateFields()
                            if (emailError.value.isEmpty() && passwordError.value.isEmpty()) {
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

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginSignupTheme {
        val navController = rememberNavController()
        LoginScreen(navController)
    }
}