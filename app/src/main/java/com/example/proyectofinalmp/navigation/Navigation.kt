package com.example.proyectofinalmp.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalmp.navigation.model.BottomBarScreen
import com.example.proyectofinalmp.ui.LoginMainApp
import com.example.proyectofinalmp.ui.RegisterMainApp
import com.example.proyectofinalmp.ui.StartMainApp
import com.example.proyectofinalmp.ui.UserChoiceMainApp
import com.example.proyectofinalmp.ui.detailrecetas.view.RecipeDetailsMainApp
import com.example.proyectofinalmp.ui.misrecetas.view.MyRecipesMainApp
import com.example.proyectofinalmp.ui.principalrecetas.view.RecetasForYouMainApp
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.AppViewModel
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.UserPreferences
import androidx.compose.runtime.livedata.observeAsState


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val userPreferences = UserPreferences(LocalContext.current)
    val isGuest = userPreferences.isGuest()
    val screens = listOf(
        BottomBarScreen.RecetasForYou,
        BottomBarScreen.MyRecipes,
    )
    val currentRoute = screens[0].route

    Scaffold(
        bottomBar = { BottomBar(navController = navController,  isClickable = !isGuest) },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        if (currentRoute == BottomBarScreen.RecetasForYou.route) {
            RecetasForYouMainApp(navController)
        } else if (currentRoute == BottomBarScreen.MyRecipes.route) {
            MyRecipesMainApp(navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController, isClickable: Boolean = true) {
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    val screens = listOf(
        BottomBarScreen.RecetasForYou,
        BottomBarScreen.MyRecipes,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = if (isClickable) Color(0xFFED7D31) else Color.Gray, //Si no es invitado, el usuario tendrá acceso a todas las funciones. De lo contrario, el bottom bar estará gris y no será clickable
        contentColor = Color.White
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
                isClickable = isClickable,
                showSnackbar = { showSnackbar = true },
                setMessage = { snackbarMessage = it }
            )
        }
    }

    if (showSnackbar) {
        Snackbar(
            action = {
                Row {
                    TextButton(onClick = {
                        showSnackbar = false
                        navController.navigate("LoginScreen")
                    }) {
                        Text(text = "Login", color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = {
                        showSnackbar = false
                        navController.navigate("RegisterScreen")
                    }) {
                        Text(text = "Sign Up", color = Color.White)
                    }
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "For full functionality, please ")
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
    isClickable: Boolean = true,
    showSnackbar: () -> Unit,
    setMessage: (String) -> Unit
) {
    BottomNavigationItem(
        label = {},
        icon = {
            Icon(
                painter = painterResource(id = screen.iconResId),
                contentDescription = "Navigation Icon",
                modifier = Modifier
                    .size(30.dp)
                    .offset(y = 20.dp)
                    .alpha(if (isClickable) 1f else 0.5f)
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = if (isClickable) LocalContentColor.current.copy(alpha = ContentAlpha.disabled) else Color.Gray,
        onClick = {
            if (isClickable) {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            } else {
                setMessage("For full functionality, please Login or Sign Up")
                showSnackbar()
            }
        }
    )
}
    @Composable
    fun Navigation(modifier: Modifier = Modifier) { //Grafo principal
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = NavigationState.StartMainApp.route,
            modifier = modifier
        ) {

            //Grafo principal
            composable(NavigationState.StartMainApp.route) { StartMainApp(navController) }
            composable(NavigationState.UserChoiceMainApp.route) { UserChoiceMainApp(navController) }
            composable(NavigationState.LoginMainApp.route) { LoginMainApp(navController) }
            composable(NavigationState.RegisterMainApp.route) { RegisterMainApp(navController) }
            composable(NavigationState.MainScreen.route) {
                MainScreen(navController)
            }
            composable(NavigationState.RecipeDetailsMainApp.route) { backStackEntry ->
                val recetaId = backStackEntry.arguments?.getString("recetaId")
                val fromScreen = backStackEntry.arguments?.getString("fromScreen")
                if (recetaId != null) {
                    RecipeDetailsMainApp(
                        navController,
                        recetaId,
                        fromScreen,
                        viewModel = AppViewModel()
                    )
                }
            }
            //BottomNavGraph
            composable(route = BottomBarScreen.RecetasForYou.route) {
                RecetasForYouMainApp(
                    navController
                )
            }
            composable(route = BottomBarScreen.MyRecipes.route) { MyRecipesMainApp(navController) }
        }
    }

