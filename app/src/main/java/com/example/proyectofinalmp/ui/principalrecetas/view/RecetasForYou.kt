package com.example.proyectofinalmp.ui.principalrecetas.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.RecetasForYouViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.Receta
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.proyectofinalmp.R
import androidx.compose.runtime.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.proyectofinalmp.navigation.model.BottomBarScreen
import com.example.proyectofinalmp.ui.principalrecetas.viewmodel.AppViewModel
import kotlinx.coroutines.delay


@Composable
fun RecetasForYouMainApp(navController: NavController) {
    val viewModel: RecetasForYouViewModel = viewModel() //Instanciar el viewModel
    val recetasPorCategoria by viewModel.recetas.observeAsState(emptyList()) //Obtener y observar la lista de recetas
    var categoriaSeleccionada by remember { mutableStateOf("Chicken") } // Categoría por defecto
    val images = listOf(R.drawable.saladz, R.drawable.foodie1, R.drawable.avocadotoast, R.drawable.kimchi) //Obtener la lista de las imagenes del carrousel
    var currentImageIndex by remember { mutableIntStateOf(0) } //Obtener el index actual de la imagen

    LaunchedEffect(key1 = currentImageIndex) {
        delay(4500L)
        currentImageIndex = (currentImageIndex + 1) % images.size
    }

    Surface(color = Color(0xFFFAF3E0)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .offset(y=(-10).dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.newestfoods1),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(350.dp, 80.dp)
            )
            // Imagen destacada
            Image(
                painter = painterResource(id = images[currentImageIndex]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(15.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            CategoriasRow(categoriaSeleccionada) { nuevaCategoria -> //Determina la categoria que es seleccionada por el usuario
                categoriaSeleccionada = nuevaCategoria
            }

            LazyColumn {//Filtra las recetas de la categoria seleccioanda
                items(recetasPorCategoria.filter { it.categoria == categoriaSeleccionada }) { receta ->
                    RecetaItem(receta = receta, navController = navController)
                }
            }
        }
    }
}

@Composable
fun RecetaItem(receta: Receta, navController: NavController) {
    val recetaId= receta.id

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable{
                navController.navigate("recipeDetail/$recetaId/RecipesForYou") //Navega hacia la pantalla de los detalles de la receta
            }
            .clip(RoundedCornerShape(12.dp)) // Bordes más redondeados
            .shadow(elevation = 10.dp), // Sombra añadida
        border=BorderStroke(1.dp, color=Color.Black),
        shape = RoundedCornerShape(12.dp),
        colors= CardDefaults.cardColors(containerColor = colorResource(id = R.color.color_card)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = receta.imagen),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = receta.titulo, fontWeight = FontWeight.Bold, fontSize = 18.sp, color= Color.Black)
                Text(text = receta.descripcion, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun CategoriasRow(categoriaSeleccionada: String, onCategoriaSelected: (String) -> Unit) {
    val categorias = listOf(stringResource(R.string.category_chicken), stringResource(R.string.category_meat), stringResource(R.string.category_pasta),stringResource(R.string.category_snacks), stringResource(R.string.category_vegan))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(categorias) { categoria ->
            Button(
                onClick = { onCategoriaSelected(categoria) },
                shape = RoundedCornerShape(12.dp),
                colors = if (categoriaSeleccionada == categoria) {
                    // Colores cuando la categoría está seleccionada
                    ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.color_button_selected))
                } else {
                    // Colores para las categorías no seleccionadas
                    ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.color_button_unselected))
                }
            ) {
                Text(text = categoria)
            }
        }
    }
}

@Preview
@Composable
fun RecetasForYouPreview() {
    val previewNavController = rememberNavController()
    RecetasForYouMainApp(navController = previewNavController)
}