package com.example.proyectofinalmp.ui

import android.util.Log
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.UserPreferences
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginMainApp(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    val userPreferences = UserPreferences(LocalContext.current)
    val context = LocalContext.current

    //String Resources
    val welcomeBack = context.getString(R.string.welcome_back)
    val emailHint = context.getString(R.string.username_hint)
    val passwordHint = context.getString(R.string.password_hint)
    val loginButton = context.getString(R.string.login_button)
    val invalidCredentials = context.getString(R.string.invalid_credentials)
    val loginFontSizeValue = context.getString(R.string.login_title_font_size).toInt()
    val loginFontSize = loginFontSizeValue.sp
    val errorMessageFontSizeValue= context.getString(R.string.error_message_font_size).toInt()
    val errorMessageFontSize= errorMessageFontSizeValue.sp

    //Dimension Resources
    val loginCardPadding = dimensionResource(R.dimen.login_card_padding)

    //Color Resources
    val backgroundColor1 = colorResource(R.color.background_color1)
    val titleColor = colorResource(R.color.title_color)
    val focusedIndicatorColor = colorResource(R.color.focused_indicator_color)
    val unfocusedIndicatorColor = colorResource(R.color.unfocused_indicator_color)
    val buttonContainerColor = colorResource(R.color.button_container_color)

    Surface(color = backgroundColor1, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // Blurred Background Image
            Image(
                painter = painterResource(id = R.drawable.saladz),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,  // Fill the screen, might crop the image
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
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(NavigationState.UserChoiceMainApp.route)
                    }
            )
            Card(
                modifier = Modifier.padding(loginCardPadding),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    Text(
                        text = welcomeBack,
                        fontSize = loginFontSize,
                        fontFamily = CalistogaFamily,
                        color = titleColor
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    //TextFields
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(emailHint, fontFamily = RobotoFontFamily) },
                        textStyle = TextStyle(color = Color.Black, fontFamily = RobotoFontFamily),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = focusedIndicatorColor,
                            unfocusedIndicatorColor = unfocusedIndicatorColor
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(passwordHint, fontFamily = RobotoFontFamily) },
                        textStyle = TextStyle(color = Color.Black, fontFamily = RobotoFontFamily),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = focusedIndicatorColor,
                            unfocusedIndicatorColor = unfocusedIndicatorColor
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            fontSize = errorMessageFontSize,
                            fontFamily = RobotoFontFamily
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener{task ->
                                    if (task.isSuccessful){
                                        Log.d("Login", "signInWithEmail:success")
                                        userPreferences.setGuest(false)
                                        navController.navigate(NavigationState.MainScreen.route)
                                    } else{
                                        Log.w("Login", "signInWithEmail:failure", task.exception)
                                        errorMessage = context.getString(R.string.failedauth)
                                    }
                                }
                            },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonContainerColor)
                    ) {
                        Text(text = loginButton, color = Color.White, fontFamily = RobotoFontFamily)
                    }
                }
            }
        }
    }
}

// Funciones de validación

fun String.isValidUsername(): Boolean {
    return this.isNotEmpty() && this.length >= 6
}

fun String.isValidPassword(): Boolean {
    return this.isNotEmpty() && this.length >= 8
}

