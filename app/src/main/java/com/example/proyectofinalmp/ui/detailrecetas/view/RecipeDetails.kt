package com.example.proyectofinalmp.ui.detailrecetas.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalmp.R
import com.example.proyectofinalmp.navigation.NavigationState
import com.example.proyectofinalmp.navigation.model.BottomBarScreen
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.AppViewModel
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.RecetasForYouViewModel

class RecipeDetails: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        val recetaId = intent.getStringExtra("recetaId")
        val fromScreen = intent.getStringExtra("fromScreen")
        super.onCreate(savedInstanceState)
        setContent{
            val navController = rememberNavController()
            RecipeDetailsMainApp(navController= navController,recetaId = recetaId, fromScreen = fromScreen, viewModel = AppViewModel())
        }
    }
}

@Composable
fun RecipeDetailsMainApp(navController: NavController, recetaId: String?, fromScreen: String?, viewModel: AppViewModel) {
    DisposableEffect(Unit) {//Definida para ver desde que pantalla se accesa a los detalles de la receta, para así navegar de regreso a esa misma pantalla
        onDispose {
            when (fromScreen) {
                "RecipesForYou" -> viewModel.setCurrentMainScreen(BottomBarScreen.RecetasForYou)
                "MyRecipes" -> viewModel.setCurrentMainScreen(BottomBarScreen.MyRecipes)
            }
        }
    }
    val viewModel: RecetasForYouViewModel = viewModel() //Inicializar viewModel
    val recetasPorId by viewModel.recetas.observeAsState(emptyList()) //Obtener y observar la lista de las recetas
    val recetaSeleccionada = recetasPorId.find { it.id == recetaId } //Seleccionar la receta a partir de su id
    var showSnackbar by remember { mutableStateOf(false) } //Muestra el snackbar cuando se haya guardado una receta
    var snackbarMessage by remember { mutableStateOf("") }

    Surface(color = Color(4293979604)) {
        Box(modifier = Modifier.fillMaxSize()) {
            val isFavorite by viewModel.favoriteRecipes.observeAsState(setOf())
            val isRecipeFavorite = isFavorite.contains(recetaId)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.arrowback),
                            contentDescription = "Back Arrow",
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .size(24.dp)
                                .clickable {
                                    val backStackEntry = navController.currentBackStackEntry
                                    val fromScreen = backStackEntry?.arguments?.getString("fromScreen")
                                    when(fromScreen) {
                                        "RecipesForYou" -> navController.navigate(NavigationState.MainScreen.route)
                                        "MyRecipes" -> navController.navigate(BottomBarScreen.MyRecipes.route)
                                        else -> navController.navigate(NavigationState.MainScreen.route) //Por defecto
                                    }
                                }

                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            onClick = { val wasAdded = viewModel.toggleFavorite(recetaId!!)
                                      if (wasAdded) {
                                          snackbarMessage= "Recipe was added to favorites!"
                                      } else{
                                          snackbarMessage= "Recipe was deleted from favorites."
                                      }
                                showSnackbar= true },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp)
                                .size(48.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = if(isRecipeFavorite == true) R.drawable.bookmark_added else R.drawable.bookmark),
                                contentDescription = "Add to favorites",
                                modifier = Modifier.size(35.dp)
                            )
                        }
                    }
                }

                if (recetaSeleccionada != null) {
                    val receta = recetaSeleccionada

                    // Título de la receta
                    item {
                        Text(
                            text = receta.titulo,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, bottom = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Imagen de la receta
                    item {
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 2.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            Image(
                                painter = painterResource(id = receta.imagen),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Sección de ingredientes
                    item {
                        // Título de los Ingredientes
                        Text(
                            text = "Ingredients:",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, bottom = 8.dp)
                        )
                        IngredientList(ingredients = receta.ingredientes)
                    }
                    // Sección de preparación
                    item {
                        DisplayPreparationSteps(steps = receta.pasos)
                    }
                } else {
                    item {
                        Text("Receta no encontrada")
                    }
                }
            }
            if (showSnackbar) {
                Snackbar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    action = {
                        Text("Cerrar", modifier = Modifier.clickable {
                            showSnackbar = false
                        })
                    }
                ) {
                    Text(text = snackbarMessage)
                }
            }
        }
    }
}

@Composable
fun IngredientList(ingredients: List<Pair<String, String>>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        ingredients.forEach { (ingredient, quantity) ->
            IngredientCard(ingredient = ingredient, quantity = quantity)
        }
    }
}

@Composable
fun IngredientCard(ingredient: String, quantity: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = ingredient,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 16.sp
        )
        Text(
            text = quantity,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}
    @Composable
    fun DisplayPreparationSteps(steps: List<String>) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(4294373870))
                .padding(16.dp)
                .border(1.dp, Color.Gray)
        ) {
            Column {

                // Título "Instructions"
                Text(
                    text = "Instructions:",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                steps.forEachIndexed { index, step ->
                    Text(
                        text = "${index + 1}. $step",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)  // Espacio entre pasos
                    )
                }
            }
        }
    }
@Preview
@Composable
fun DetailPreview() {
    val previewNavController = rememberNavController()
    val fromScreen= "fromScreen"
    RecipeDetailsMainApp(navController = previewNavController, recetaId = "1", fromScreen = fromScreen, viewModel = AppViewModel())
}


