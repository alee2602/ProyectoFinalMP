package com.example.proyectofinalmp.ui

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectofinalmp.R
import com.example.proyectofinalmp.navigation.NavigationState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterMainApp(navController: NavController) {
    //Estados para todos los campos en el que el usuario ingresará toda su información
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showMessage by remember { mutableStateOf(false) }
    var registrationSuccess by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    // String Resources
    val newHereString = context.getString(R.string.new_here)
    val usernameHint = context.getString(R.string.username_hint1)
    val emailAddressHint = context.getString(R.string.email_address_hint)
    val passwordHint = context.getString(R.string.password_hint1)
    val confirmPasswordHint = context.getString(R.string.confirm_password_hint)
    val registerString = context.getString(R.string.register)
    val successfullyRegisteredString = context.getString(R.string.successfully_registered)
    val passwordsDoNotMatchString = context.getString(R.string.passwords_do_not_match)
    val invalidUsernameOrPasswordString = context.getString(R.string.invalid_username_or_password)
    val invalidEmailString= context.getString(R.string.invalid_email)

    // Dimen Resources
    val registerCardPadding = dimensionResource(R.dimen.register_card_padding)
    val spacerHeight = dimensionResource(R.dimen.spacer_height)
    val backArrowPadding = dimensionResource(R.dimen.back_arrow_padding)

    // Color Resources
    val registerSurfaceColor = colorResource(R.color.register_surface_color)
    val registerTitleColor = colorResource(R.color.register_title_color)
    val registerFocusedIndicatorColor = colorResource(R.color.register_focused_indicator_color)
    val registerUnfocusedIndicatorColor = colorResource(R.color.register_unfocused_indicator_color)
    val registerButtonColor = colorResource(R.color.register_button_color)


    Surface(color = registerSurfaceColor, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // Blurred Background Image
            Image(
                painter = painterResource(id = R.drawable.saladz),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(
                        radiusX = 10.dp,
                        radiusY = 10.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(0.dp))
                    ),
            )
            Image(
                painter = painterResource(id = R.drawable.arrowback),
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(backArrowPadding)
                    .clickable {
                        navController.navigate(NavigationState.UserChoiceMainApp.route)
                    }
            )
            Card(
                modifier = Modifier.padding(registerCardPadding),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ){
                    Text(
                        text = newHereString,
                        fontSize = 32.sp,
                        fontFamily = CalistogaFamily,
                        color = registerTitleColor
                    )
                    Spacer(modifier = Modifier.height(spacerHeight))

                    //TextFields para cada atributo

                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text(usernameHint, fontFamily = RobotoFontFamily) },
                        textStyle = TextStyle(color = Color.Black, fontFamily = RobotoFontFamily),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = registerFocusedIndicatorColor,
                            unfocusedIndicatorColor = registerUnfocusedIndicatorColor
                        )
                    )
                    Spacer(modifier = Modifier.height(spacerHeight))

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(emailAddressHint, fontFamily = RobotoFontFamily) },
                        textStyle = TextStyle(color = Color.Black, fontFamily = RobotoFontFamily),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = registerFocusedIndicatorColor,
                            unfocusedIndicatorColor = registerUnfocusedIndicatorColor
                        )
                    )

                    Spacer(modifier = Modifier.height(spacerHeight))

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(passwordHint, fontFamily = RobotoFontFamily) },
                        textStyle = TextStyle(color = Color.Black, fontFamily = RobotoFontFamily),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = registerFocusedIndicatorColor,
                            unfocusedIndicatorColor = registerUnfocusedIndicatorColor
                        )
                    )
                    Spacer(modifier = Modifier.height(spacerHeight))

                    TextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text(confirmPasswordHint, fontFamily = RobotoFontFamily) },
                        textStyle = TextStyle(color = Color.Black, fontFamily = RobotoFontFamily),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = registerFocusedIndicatorColor,
                            unfocusedIndicatorColor = registerUnfocusedIndicatorColor
                        )
                    )
                    Spacer(modifier = Modifier.height(spacerHeight))

                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            fontSize = 16.sp,
                            fontFamily = RobotoFontFamily
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (username.isValidUsername() && password.isValidPassword() && email.isValidEmail() && doPasswordsMatch(password, confirmPassword)) {
                                errorMessage = ""
                                //Llamar a Firebase para crear un usuario
                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener{task ->
                                        if (task.isSuccessful){
                                            registrationSuccess = true  // Establece el registro como exitoso
                                            showMessage = true // Mostrar el Snackbar
                                        } else{
                                            errorMessage = task.exception?.message ?: invalidUsernameOrPasswordString
                                        }
                                    }

                            } else {
                                errorMessage = when {
                                    !username.isValidUsername() -> context.getString(R.string.invalid_username_format)
                                    !email.isValidEmail() -> invalidEmailString
                                    !password.isValidPassword() -> context.getString(R.string.weak_password)
                                    !doPasswordsMatch(password, confirmPassword) -> passwordsDoNotMatchString
                                    else -> invalidUsernameOrPasswordString
                                }
                            }
                        },

                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = registerButtonColor)
                    ) {
                        Text(text = registerString, color = Color.White, fontFamily = RobotoFontFamily)
                    }
                }
                LaunchedEffect(registrationSuccess) {
                    if (registrationSuccess) {
                        delay(2000) // Espera 2 segundos
                        navController.navigate("LoginScreen")
                    }
                }
            }
            if (showMessage) {
                Snackbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter) // Asegura que el Snackbar aparezca en la parte inferior
                        .padding(16.dp),
                    content = {
                        Text(text = successfullyRegisteredString)
                    },
                    action = {
                        TextButton(onClick = { showMessage = false }) {
                            Text("Ok")
                        }
                    }
                )
            }
        }
    }
}

fun doPasswordsMatch(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}
fun String.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

