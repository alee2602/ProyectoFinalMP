package com.example.proyectofinalmp.navigation

sealed class NavigationState(val route: String){ //Sealed Class del grafo principal
    object StartMainApp: NavigationState("StartScreen")
    object UserChoiceMainApp: NavigationState("UserChoiceScreen")
    object RegisterMainApp: NavigationState("RegisterScreen")
    object LoginMainApp: NavigationState("LoginScreen")
    object MainScreen: NavigationState("MainScreen")
    object RecipeDetailsMainApp : NavigationState("recipeDetail/{recetaId}/{fromScreen}")
}
