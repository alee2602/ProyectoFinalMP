package com.example.proyectofinalmp.ui.misrecetas.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.Receta
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.RecetasForYouViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalmp.R
import com.example.proyectofinalmp.navigation.NavigationState


@Composable
fun MyRecipesMainApp(navController: NavController) {
    val viewModel: RecetasForYouViewModel= viewModel()
    val favoriteRecipesIds by viewModel.favoriteRecipes.observeAsState(emptyList())
    val allRecipes = viewModel.recetas.value ?: emptyList()

    // Filtrar solo las recetas que están marcadas como favoritas
    val favoriteRecipes = allRecipes.filter { it.id in favoriteRecipesIds }

    Surface(color = Color(4293979604)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Flecha de regreso
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrowback),
                    contentDescription = "Back Arrow",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.navigate(NavigationState.MainScreen.route) }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Mostrar la imagen "myrecipes"
            Image(
                painter = painterResource(id = R.drawable.myrecipes),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(350.dp, 80.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Lista de recetas
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(favoriteRecipes) { recipe ->
                    RecipeCard(recipe, navController)
                }
            }
        }
    }
}
@Composable
fun RecipeCard(recipe: Receta, navController: NavController) {
    val recetaId= recipe.id

    Card(modifier = Modifier
        .padding(8.dp)
        .clickable {
            navController.navigate("recipeDetail/$recetaId/MyRecipes") //Navega hacia los detalles de las recetas desde MyRecipesMainApp
        }
        .fillMaxWidth(),
        colors= CardDefaults.cardColors(containerColor = Color(4293580731)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),) {
        Column {
            // Mostrar la imagen de la receta
            Image(
                painter = painterResource(id = recipe.imagen),
                contentDescription = "Imagen de ${recipe.titulo}",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Título de la receta
            Text(text = recipe.titulo, fontWeight= FontWeight.Bold, color= Color.DarkGray, modifier = Modifier.padding(8.dp))

            Spacer(modifier = Modifier.height(4.dp))

            // Descripción de la receta
            Text(text = recipe.descripcion, fontWeight= FontWeight.Bold, modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview
@Composable
fun MyRecipesPreview(){
    val previewNavController = rememberNavController()
    MyRecipesMainApp(navController = previewNavController)
}

