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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.proyectofinalmp.ui.misrecetas.viewmodel.MyRecipesViewModel
import com.example.proyectofinalmp.ui.repository.Receta


@Composable
fun MyRecipesMainApp(navController: NavController) {
    val viewModel: MyRecipesViewModel= viewModel()
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
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("recipeDetail/${recipe.id}/MyRecipes")
            }
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors= CardDefaults.cardColors(containerColor = Color(0xFFF0EAE2)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = recipe.imagen),
                contentDescription = "Imagen de ${recipe.titulo}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(
                text = recipe.titulo,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
            Text(
                text = recipe.descripcion,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun MyRecipesPreview(){
    val previewNavController = rememberNavController()
    MyRecipesMainApp(navController = previewNavController)
}

