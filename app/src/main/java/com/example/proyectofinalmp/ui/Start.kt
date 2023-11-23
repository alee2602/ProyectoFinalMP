package com.example.proyectofinalmp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalmp.R
import com.example.proyectofinalmp.navigation.NavigationState
import androidx.compose.ui.unit.sp


//Font Families
val RobotoFontFamily= FontFamily(Font(R.font.robotobolditallic))

val CalistogaFamily= FontFamily(Font(R.font.calistogaregular))

@Composable
fun StartMainApp(navController: NavController) {
    val context = LocalContext.current

    //String Resources
    val startCookingText = context.getString(R.string.start_cooking)

    //Dimesion Resources
    val spacingLarge = dimensionResource(R.dimen.spacing_large)
    val logoSize = dimensionResource(R.dimen.logo_size)
    val buttonHeight = dimensionResource(R.dimen.button_height)
    val buttonFontSizeValue = stringResource(R.string.button_font_size).toInt()
    val buttonFontSize = buttonFontSizeValue.sp
    val paddingDefault = dimensionResource(R.dimen.padding_default)

    //Color Resources
    val backgroundColor = colorResource(R.color.background_color)
    val buttonColor = colorResource(R.color.button_color)
    val textColorWhite = colorResource(R.color.text_color_white)

    Spacer(modifier = Modifier.height(spacingLarge))

    Surface(color = backgroundColor, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingDefault)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo1), //Logo
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(logoSize)
            )
            Spacer(modifier = Modifier.height(spacingLarge))

            Button(
                onClick = { navController.navigate(NavigationState.UserChoiceMainApp.route) }, //Ruta hacia UserMainChoice
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(buttonHeight)
            ) {
                Text(
                    text = startCookingText,
                    color = textColorWhite,
                    fontSize = buttonFontSize,
                    fontWeight = FontWeight.Bold,
                    fontFamily = RobotoFontFamily
                )
            }

            Spacer(modifier = Modifier.height(spacingLarge))
        }
    }
}

@Preview
@Composable
fun StartPreview() {
    val previewNavController = rememberNavController()
    StartMainApp(navController = previewNavController)
}
