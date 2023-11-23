package com.example.proyectofinalmp.ui.detailrecetas.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
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
import com.example.proyectofinalmp.ui.detailrecetas.viewmodel.RecipeDetailsViewModel
import com.example.proyectofinalmp.ui.misrecetas.viewmodel.MyRecipesViewModel
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.AppViewModel
import com.example.proyectofinalmp.ui.repository.InformacionNutricional

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
    val viewModel: RecipeDetailsViewModel = viewModel() //Inicializar viewModel
    val recetasPorId by viewModel.recetas.observeAsState(emptyList()) //Obtener y observar la lista de las recetas
    val recetaSeleccionada = recetasPorId.find { it.id == recetaId } //Seleccionar la receta a partir de su id
    var showSnackbar by remember { mutableStateOf(false) } //Muestra el snackbar cuando se haya guardado una receta
    var snackbarMessage by remember { mutableStateOf("") }
    val context= LocalContext.current

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
                                    val fromScreen =
                                        backStackEntry?.arguments?.getString("fromScreen")
                                    when (fromScreen) {
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
                                          snackbarMessage= context.getString(R.string.wasAdded)
                                      } else{
                                          snackbarMessage= context.getString(R.string.wasnotAdded)
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
                    // Sección de Alergenos
                    item {
                        receta.alergenos?.let { DisplayAllergens(alergenos = it) }
                    }
                    // Sección de Información Nutricional
                    item {
                        NutritionalInfoSection(nutritionalInfo = receta.infoNutricional)
                    }
                    // Sección de ingredientes
                    item {
                        // Título de los Ingredientes
                        Text(
                            text = context.getString(R.string.ingredientstitle),
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
                        Text(context.getString(R.string.recipe_not_found))
                    }
                }
            }
            if (showSnackbar) {
                Snackbar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    action = {
                        Text(context.getString(R.string.close_snackbar), modifier = Modifier.clickable {
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
    fun DisplayPreparationSteps(steps: List<String>) { //Despliega todas las recetas
        val context= LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(4294373870))
                .padding(16.dp)
                .border(1.dp, Color.Gray)
        ) {
            Column {

                Text(
                    text = context.getString(R.string.recipe_prep),
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
                            .padding(8.dp)
                    )
                }
            }
        }
    }

@Composable
fun NutritionalInfoSection(nutritionalInfo: InformacionNutricional) { //Muestra la sección de infrmación nutricional
    val context= LocalContext.current
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors= CardDefaults.cardColors(containerColor = Color(4294180328)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = context.getString(R.string.nutritionalinfo),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            NutritionalInfoRow(
                iconId = R.drawable.apple,
                contentDescription = "Calorías",
                infoText = "${context.getString(R.string.calories)} ${nutritionalInfo.calorias}"
            )
            NutritionalInfoRow(
                iconId = R.drawable.chicken,
                contentDescription = "Proteínas",
                infoText = "${context.getString(R.string.proteins)} ${nutritionalInfo.proteinas}"
            )
            NutritionalInfoRow(
                iconId = R.drawable.oil,
                contentDescription = "Grasas",
                infoText = "${context.getString(R.string.fats)} ${nutritionalInfo.grasas}"
            )
            NutritionalInfoRow(
                iconId = R.drawable.bread,
                contentDescription = "Carbohidratos",
                infoText = "${context.getString(R.string.carbs)} ${nutritionalInfo.carbohidratos}"
            )
        }
    }
}

@Composable
fun NutritionalInfoRow(iconId: Int, contentDescription: String, infoText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = infoText,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.Black
        )
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun DisplayAllergens(alergenos: List<String>) { //Muestra los alérgenos
    val context= LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = context.getString(R.string.allergens), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        if (alergenos.isEmpty()) {
            Text(text = context.getString(R.string.noallergens), fontStyle = FontStyle.Italic)
        } else {
            alergenos.forEach { alergeno ->
                ChipView(text = alergeno)
            }
        }
    }
}

@Composable
fun ChipView(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp)
            .background(Color.Red.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, color = Color.Red)
    }
}
@Preview
@Composable
fun DetailPreview() {
    val previewNavController = rememberNavController()
    val fromScreen= "fromScreen"
    RecipeDetailsMainApp(navController = previewNavController, recetaId = "1", fromScreen = fromScreen, viewModel = AppViewModel())
}


