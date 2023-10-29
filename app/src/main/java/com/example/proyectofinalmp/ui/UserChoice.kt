package com.example.proyectofinalmp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinalmp.R
import com.example.proyectofinalmp.navigation.NavigationState
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.UserPreferences



@Composable
fun UserChoiceMainApp(navController: NavController) {
    val userPreferences = UserPreferences(LocalContext.current)
    val context = LocalContext.current

    // String Resources
    val loginText = stringResource(R.string.login_text)
    val signUpText = stringResource(R.string.sign_up_text)
    val guestText = stringResource(R.string.enter_as_guest_text)

    //Dimension Resources
    val spacingLarge = dimensionResource(R.dimen.spacing_large1)
    val imageWidth = dimensionResource(R.dimen.image_width)
    val imageHeight = dimensionResource(R.dimen.image_height)
    val buttonWidthFraction = context.resources.getFloat(R.dimen.button_width_fraction)
    val buttonHeight = dimensionResource(R.dimen.button_height1)

    //Color Resources
    val backgroundColor = colorResource(R.color.background_color)
    val buttonColor = colorResource(R.color.button_color)

    Surface(color = backgroundColor, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.userchoice1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(imageWidth, imageHeight)
                    .offset(y = (-75).dp)
            )

            Button(onClick = {
                userPreferences.setGuest(false)
                navController.navigate(NavigationState.LoginMainApp.route) //Se dirige a la pantalla de Login
            },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .fillMaxWidth(buttonWidthFraction)
                    .height(buttonHeight),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ){
                Text(text = loginText, fontFamily = RobotoFontFamily)
            }

            Spacer(modifier = Modifier.height(spacingLarge))

            Button(onClick = {
                userPreferences.setGuest(false)
                navController.navigate(NavigationState.RegisterMainApp.route) //Se dirige a la pantalla de Register
            },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .fillMaxWidth(buttonWidthFraction)
                    .height(buttonHeight),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ){
                Text(text = signUpText, fontFamily = RobotoFontFamily)
            }

            Spacer(modifier = Modifier.height(spacingLarge))

            Button(onClick = {
                userPreferences.setGuest(true)
                navController.navigate("MainScreen") //Se dirige a la pantalla de MainScreen, la cual contiene el Bottom Navigation Bar
            },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .fillMaxWidth(buttonWidthFraction)
                    .height(buttonHeight),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ){
                Text(text = guestText, fontFamily = RobotoFontFamily)
            }

            Spacer(modifier = Modifier.height(spacingLarge))
        }
    }
}