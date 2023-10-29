package com.example.proyectofinalmp.navigation.model

import com.example.proyectofinalmp.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val iconResId: Int,
){
    object RecetasForYou: BottomBarScreen(
        route="recetasforyou",
        title="RecetasForYou",
        iconResId= R.drawable.home
    )

    object MyRecipes: BottomBarScreen(
        route="misrecetas",
        title="MyRecipes",
        iconResId= R.drawable.bookmark
    )
}
