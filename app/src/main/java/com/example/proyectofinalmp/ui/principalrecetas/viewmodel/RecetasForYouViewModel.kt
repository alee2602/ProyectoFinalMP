package com.example.proyectofinalmp.ui.principalrecetas.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectofinalmp.R
import com.example.proyectofinalmp.navigation.model.BottomBarScreen

data class Receta(
    val id: String,
    val categoria: String,
    val titulo: String,
    val imagen: Int,
    val descripcion: String,
    val ingredientes:  List<Pair<String, String>>, //Lista de ingredientes en formato pair (ingrediente, medida)
    val pasos:List<String> //Lista con todos los pasos
)
class AppViewModel : ViewModel() {
    var currentMainScreen: BottomBarScreen = BottomBarScreen.RecetasForYou // Inicializar con la pantalla predeterminada
        private set

    // Función para cambiar la pantalla principal actual
    fun setCurrentMainScreen(screen: BottomBarScreen) {
        currentMainScreen = screen
    }
}

class RecetasForYouViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext //Instanciar sharedpreferences para las recetas que el usuario guarde
    private val sharedPreferences = context.getSharedPreferences("RecetasFavorites", Context.MODE_PRIVATE)
    val recetas: MutableLiveData<List<Receta>> = MutableLiveData()

    // Añadir el MutableState para recetas favoritas
    private val _favoriteRecipes = MutableLiveData<Set<String>>(emptySet())
    val favoriteRecipes: LiveData<Set<String>> get() = _favoriteRecipes


    init {
        cargarRecetas()
        loadFavoritesFromSharedPreferences()
    }

    private fun cargarRecetas() {
        recetas.value = listOf(
            // Chicken recipesz
            //Receta1
            Receta(
                id = "1",
                categoria = "Chicken",
                titulo = "Grilled Chicken Salad",
                imagen = R.drawable.grilledchickensalad,
                descripcion = "A light and healthy salad with grilled chicken breast.",
                ingredientes = listOf(
                    Pair("Chicken breast", "2 pieces"),
                    Pair("Lettuce", "1 cup"),
                    Pair("Cherry tomatoes", "10 pieces"),
                    Pair("Cucumber", "1/2 piece"),
                    Pair("Olive oil", "2 tablespoons")
                ),
                pasos = listOf(
                    "Grill the chicken breast until fully cooked.",
                    "Chop the lettuce, tomatoes, and cucumber.",
                    "Mix all ingredients in a bowl.",
                    "Drizzle with olive oil and serve."
                )
            ),

            //Receta2
            Receta(
                id = "2",
                categoria = "Chicken",
                titulo = "Chicken Stir-Fry",
                imagen = R.drawable.chickenstirfry,
                descripcion = "A quick and easy stir-fry with chicken and vegetables.",
                ingredientes = listOf(
                    Pair("Chicken breast", "1 pound, cut into pieces"),
                    Pair("Broccoli florets", "1 cup"),
                    Pair("Red bell pepper, chopped", "1/2 cup"),
                    Pair("Onion, chopped", "1/2 cup"),
                    Pair("Ginger, minced", "1 tablespoon"),
                    Pair("Garlic, minced", "2 cloves"),
                    Pair("Soy sauce", "2 tablespoons"),
                    Pair("Sesame oil", "1 tablespoon"),
                    Pair(" cornstarch", "1 tablespoon"),
                    Pair("Water", "1/4 cup"),
                    Pair("Salt and pepper", "To taste")
                ),
                pasos = listOf(
                    "Heat a large skillet or wok over medium-high heat.",
                    "Add the sesame oil and chicken to the skillet and cook until the chicken is browned on all sides.",
                    "Add the broccoli, bell pepper, onion, ginger, and garlic to the skillet and cook until the vegetables are tender.",
                    "In a small bowl, whisk together the soy sauce, cornstarch, and water. Add the sauce to the skillet and cook until the sauce has thickened.",
                    "Season with salt and pepper to taste.",
                    "Serve immediately with rice or noodles."
                )
            ),

            //Receta3
            Receta(
                id = "3",
                categoria = "Chicken",
                titulo = "Chicken Curry",
                imagen = R.drawable.chickencurry,
                descripcion = "A flavorful and fragrant curry dish with chicken and vegetables.",
                ingredientes = listOf(
                    Pair("Chicken breast", "1 pound, cut pieces"),
                    Pair("Onion, chopped", "1/2 cup"),
                    Pair("Garlic, minced", "2 cloves"),
                    Pair("Ginger, minced", "1 tablespoon"),
                    Pair("Curry powder", "2 tablespoons"),
                    Pair("Cumin", "1 teaspoon"),
                    Pair("Tumeric", "1 teaspoon"),
                    Pair("Chili powder", "1/2 teaspoon"),
                    Pair("Coconut milk", "1 can (14 ounces)"),
                    Pair("Chicken broth", "1/2 cup"),
                    Pair("Potatoes", "peeled and diced, 1 pound"),
                    Pair("Carrots", "diced, 1 cup"),
                    Pair("Peas", "1/2 cup"),
                    Pair("Salt and pepper", "To taste")
                ),
                pasos = listOf(
                    "Heat a large pot or Dutch oven over medium heat.",
                    "Add the oil and chicken to the pot and cook until the chicken is browned on all sides.",
                    "Add the onion, garlic, ginger, curry powder, cumin, turmeric, and chili powder to the pot and cook for 1 minute, stirring constantly.",
                    "Stir in the coconut milk and chicken broth. Bring to a boil, then reduce heat to low and simmer for 20 minutes, or until the chicken and potatoes are cooked through.",
                    "Add the carrots and peas to the pot and simmer for an additional 10 minutes, or until the vegetables are tender.",
                    "Season with salt and pepper to taste.",
                    "Serve with rice or naan bread."
                )
            ),

            //Receta 4
            Receta(
                id = "4",
                categoria = "Chicken",
                titulo = "Chicken and Vegetable Soup",
                imagen = R.drawable.chickensoup,
                descripcion = "A hearty and healthy soup with chicken and vegetables.",
                ingredientes = listOf(
                    Pair("Chicken breast", "1 pound, boneless and skinless"),
                    Pair("Onion, chopped", "1/2 cup"),
                    Pair("Carrots, chopped", "1 cup"),
                    Pair("Celery stalks, chopped", "1 cup"),
                    Pair("Garlic, minced", "2 cloves"),
                    Pair("Chicken broth", "6 cups"),
                    Pair("Bay leaves", "2"),
                    Pair("Dried thyme", "1 teaspoon"),
                    Pair("Salt and pepper", "To taste")
                ),
                pasos = listOf(
                    "Place the chicken breast in a large pot or Dutch oven and cover with water.",
                    "Bring the water to a boil, then reduce heat to low and simmer for 15 minutes, or until the chicken is cooked through.",
                    "Remove the chicken from the pot and let it cool slightly.",
                    "Once the chicken has cooled, shred it using two forks.",
                    "Return the shredded chicken to the pot.",
                    "Add the onion, carrots, celery, garlic, chicken broth, bay leaves, and thyme to the pot. Bring to a boil, then reduce heat to low and simmer for 30 minutes, or until the vegetables are tender.",
                    "Season with salt and pepper to taste.",
                    "Serve hot."
                )
            ),

            //Receta 5
            Receta(
                id = "5",
                categoria = "Chicken",
                titulo = "Chicken Piccata",
                imagen = R.drawable.chickenpiccata,
                descripcion = "A classic Italian dish with chicken, lemon, and capers.",
                ingredientes = listOf(
                    Pair("Chicken breast", "4 pieces, pounded thin"),
                    Pair("All-purpose flour", "1/2 cup"),
                    Pair("Salt and pepper", "To taste"),
                    Pair("Olive oil", "2 tablespoons"),
                    Pair("Unsalted butter", "2 tablespoons"),
                    Pair("Lemon juice", "1/4 cup"),
                    Pair("Chicken broth", "1/2 cup"),
                    Pair("Capers, drained", "2 tablespoons"),
                    Pair("Parsley, chopped", "1/4 cup")
                ),
                pasos = listOf(
                    "Dredge the chicken in flour and season with salt and pepper.",
                    "Heat the olive oil and butter in a large skillet over medium-high heat.",
                    "Add the chicken to the skillet and cook for 3-4 minutes per side, or until golden brown and cooked through.",
                    "Remove the chicken from the skillet and set aside.",
                    "To the same skillet, add the lemon juice and chicken broth. Bring to a boil, then reduce heat to low and simmer for 1-2 minutes, or until the sauce has thickened slightly.",
                    "Return the chicken to the skillet and cook for an additional 1 minute, to heat through.",
                    "Stir in the capers and parsley.",
                    "Serve immediately with rice or pasta."
                )
            ),

            //Meat Recipes

            Receta(
                id = "6",
                categoria = "Meat",
                titulo = "Beef Steak",
                imagen = R.drawable.beefsteak,
                descripcion = "A simple and delicious steak that is perfect for a quick and easy meal.",
                ingredientes = listOf(
                    Pair("Beef steak", "1 pound, 1-inch thick"),
                    Pair("Salt and pepper", "To taste"),
                    Pair("Olive oil", "2 tablespoons"),
                    Pair("Butter", "1 tablespoon"),
                    Pair("Garlic, minced", "2 cloves"),
                    Pair("Onion, thinly sliced", "1/2 cup"),
                    Pair("Green pepper, thinly sliced", "1/2 cup"),
                    Pair("Portobello mushrooms, sliced", "1 cup"),
                    Pair("Red wine", "1/2 cup"),
                    Pair("Beef broth", "1/2 cup"),
                    Pair("Italian seasoning", "1 teaspoon"),
                    Pair("Fresh parsley, chopped", "1/4 cup")
                ),
                pasos = listOf(
                    "Season the steak with salt and pepper.",
                    "Heat the olive oil and butter in a large skillet over medium-high heat.",
                    "Add the steak to the skillet and cook for 3-4 minutes per side, or until cooked to desired doneness.",
                    "Remove the steak from the skillet and set aside.",
                    "Add the garlic, onion, and green pepper to the skillet and cook for 5-7 minutes, or until softened.",
                    "Add the mushrooms and cook for an additional 5 minutes, or until softened.",
                    "Deglaze the pan with the red wine.",
                    "Add the beef broth and Italian seasoning. Bring to a boil, then reduce heat to low and simmer for 10 minutes, or until the sauce has thickened slightly.",
                    "Return the steak to the skillet and cook for an additional 2-3 minutes, to heat through.",
                    "Garnish with parsley and serve."
                )
            ),

            Receta(
                id = "7",
                categoria = "Meat",
                titulo = "Pork Chops with Apples and Onions",
                imagen = R.drawable.porkchops,
                descripcion = "A classic dish that is perfect for a fall meal.",
                ingredientes = listOf(
                    Pair("Pork chops", "4 pieces, 1-inch thick"),
                    Pair("Salt and pepper", "To taste"),
                    Pair("Olive oil", "2 tablespoons"),
                    Pair("Apples, peeled, cored, and sliced", "2"),
                    Pair("Onions, thinly sliced", "1"),
                    Pair("Brown sugar", "1 tablespoon"),
                    Pair("Cinnamon", "1 teaspoon"),
                    Pair("Cloves", "2"),
                    Pair("Red wine", "1/2 cup"),
                    Pair("Beef broth", "1/2 cup"),
                    Pair("Fresh parsley, chopped", "1/4 cup")
                ),
                pasos = listOf(
                    "Season the pork chops with salt and pepper.",
                    "Heat the olive oil in a large skillet over medium-high heat.",
                    "Add the pork chops to the skillet and cook for 3-4 minutes per side, or until browned.",
                    "Remove the pork chops from the skillet and set aside.",
                    "Add the apples, onions, brown sugar, cinnamon, and cloves to the skillet. Cook for 5-7 minutes, or until the apples are softened.",
                    "Deglaze the pan with the red wine.",
                    "Add the beef broth. Bring to a boil, then reduce heat to low and simmer for 10 minutes, or until the sauce has thickened slightly.",
                    "Return the pork chops to the skillet and cook for an additional 2-3 minutes, to heat through.",
                    "Garnish with parsley and serve."
                )
            ),

            Receta(
                id = "8",
                categoria = "Meat",
                titulo = "Ground Beef Tacos",
                imagen = R.drawable.groundbeeftacos,
                descripcion = "A classic Mexican dish that is easy to make and perfect for a crowd.",
                ingredientes = listOf(
                    Pair("Ground beef", "1 pound"),
                    Pair("Onion, chopped", "1/2 cup"),
                    Pair("Green pepper, chopped", "1/2 cup"),
                    Pair("Taco seasoning", "1 packet"),
                    Pair("Water", "1/2 cup"),
                    Pair("Corn tortillas", "12"),
                    Pair("Shredded lettuce", "1 cup"),
                    Pair("Diced tomatoes", "1 cup"),
                    Pair("Grated cheddar cheese", "1 cup"),
                    Pair("Sour cream", "To taste"),
                    Pair("Guacamole", "To taste")
                ),
                pasos = listOf(
                    "Brown the ground beef in a large skillet over medium-high heat.",
                    "Drain off any excess grease.",
                    "Add the onion, green pepper, and taco seasoning to the skillet and cook for 5-7 minutes, or until the vegetables are softened.",
                    "Add the water and bring to a boil. Reduce heat to low and simmer for 10 minutes, or until the sauce has thickened slightly.",
                    "Warm the tortillas according to package directions.",
                    "To assemble the tacos, place a spoonful of the ground beef mixture in the center of each tortilla. Top with lettuce, tomatoes, cheese, sour cream, and guacamole."
                )
            ),
            Receta(
                id = "9",
                categoria = "Meat",
                titulo = "Beef Stroganoff",
                imagen = R.drawable.beefstroganoff,
                descripcion = "A hearty and flavorful dish that is perfect for a cold winter day.",
                ingredientes = listOf(
                    Pair("Beef sirloin steak", "1 pound, thinly sliced"),
                    Pair("Flour", "2 tablespoons"),
                    Pair("Salt and pepper", "To taste"),
                    Pair("Oil", "2 tablespoons"),
                    Pair("Onion, chopped", "1/2 cup"),
                    Pair("Garlic, minced", "2 cloves"),
                    Pair("Beef broth", "2 cups"),
                    Pair("Heavy cream", "1 cup"),
                    Pair("Dill weed", "1/4 cup"),
                    Pair("Capers", "1 tablespoon"),
                    Pair("Pasta, cooked", "1 pound")
                ),
                pasos = listOf(
                    "In a large bowl, combine the beef, flour, salt, and pepper. Toss to coat.",
                    "Heat the oil in a large skillet over medium-high heat. Add the beef and cook for 5-7 minutes, or until browned.",
                    "Remove the beef from the skillet and set aside.",
                    "Add the onion and garlic to the skillet and cook for 5-7 minutes, or until softened.",
                    "Add the beef broth and bring to a boil. Reduce heat to low and simmer for 10 minutes, or until the sauce has thickened slightly.",
                    "Return the beef to the skillet.",
                    "Add the heavy cream, dill weed, and capers. Heat through.",
                    "Serve over cooked pasta."
                )
            ),
            Receta(
                id = "10",
                categoria = "Meat",
                titulo = "Beef Bourguignon",
                imagen = R.drawable.beefbourguignon,
                descripcion = "A classic French dish that is slow-cooked to perfection. The beef is tender and the sauce is rich and flavorful.",
                ingredientes = listOf(
                    Pair("Beef chuck roast", "2 pounds, cut into cubes"),
                    Pair("All-purpose flour", "1/4 cup"),
                    Pair("Salt", "1 teaspoon"),
                    Pair("Black pepper", "1/2 teaspoon"),
                    Pair("Olive oil", "2 tablespoons"),
                    Pair("Large onion, chopped", "1"),
                    Pair("Carrots, chopped", "2"),
                    Pair("Celery stalks, chopped", "2"),
                    Pair("Garlic cloves, minced", "2"),
                    Pair("Red wine", "1 (750-ml) bottle"),
                    Pair("Beef broth", "1 cup"),
                    Pair("Tomato paste", "1 tablespoon"),
                    Pair("Bay leaf", "1"),
                    Pair("Dried thyme", "1/2 teaspoon"),
                    Pair("Dried rosemary", "1/2 teaspoon"),
                    Pair("Fresh parsley, chopped", "1/4 cup")
                ),
                pasos = listOf(
                    "Preheat oven to 300 degrees F (150 degrees C).",
                    "In a large bowl, combine the beef, flour, salt, and pepper. Toss to coat.",
                    "Heat the olive oil in a large Dutch oven over medium-high heat. Add the beef and cook in batches, stirring occasionally, until browned on all sides.",
                    "Add the onion, carrots, celery, and garlic to the pot. Cook, stirring occasionally, until the vegetables are softened, about 5 minutes.",
                    "Add the wine, broth, tomato paste, bay leaf, thyme, and rosemary to the pot. Bring to a boil, then reduce heat to low and cover.",
                    "Simmer in the preheated oven for 2-3 hours, or until the beef is tender.",
                    "Stir in the parsley and serve."
                )
            ),

            //Snacks
            Receta(
                id = "11",
                categoria = "Snacks",
                titulo = "Yogurt Parfaits",
                imagen = R.drawable.yogurtparfait,
                descripcion = "A delicious and nutritious snack that is perfect for any time of day.",
                ingredientes = listOf(
                    Pair("Yogurt", "1 cup"),
                    Pair("Granola", "1/2 cup"),
                    Pair("Fruit, chopped", "1/2 cup")
                ),
                pasos = listOf(
                    "In a glass or bowl, layer the yogurt, granola, and fruit.",
                    "Repeat layers until the glass or bowl is full.",
                    "Enjoy!"
                )
            ),

            Receta(
                id = "12",
                categoria = "Snacks",
                titulo = "Trail Mix",
                imagen = R.drawable.trailmix,
                descripcion = "A filling and satisfying snack that is perfect for a hike or picnic.",
                ingredientes = listOf(
                    Pair("Nuts", "1 cup"),
                    Pair("Dried fruit", "1 cup"),
                    Pair("Seeds", "1/2 cup"),
                    Pair("Chocolate chips or M&M´s", "1/4 cup")
                ),
                pasos = listOf(
                    "In a bowl, combine the nuts, dried fruit, seeds, and chocolate chips.",
                    "Mix well and enjoy!"
                )
            ),

            Receta(
                id = "13",
                categoria = "Snacks",
                titulo = "Apple Strudel Bites",
                imagen = R.drawable.applestrudelbites,
                descripcion = "A delicious and portable snack that is perfect for on the go.",
                ingredientes = listOf(
                    Pair("Apples, diced into small pieces", "2"),
                    Pair("Brown sugar", "1/4 cup"),
                    Pair("Ground cinnamon", "1/2 teaspoon"),
                    Pair("Melted butter", "2 tablespoons"),
                    Pair("Puff pastry dough, thawed", "1 package"),
                    Pair("Egg, beaten", "1")
                ),
                pasos = listOf(
                    "Preheat the oven to 375 degrees F (190 degrees C).",
                    "In a small bowl, combine the apples, brown sugar, cinnamon, and melted butter.",
                    "Roll out the puff pastry dough on a lightly floured surface and cut it into 3-inch squares.",
                    "Place a spoonful of the apple mixture in the center of each puff pastry square.",
                    "Fold the puff pastry squares into triangles, sealing the edges with a fork.",
                    "Place the puff pastry triangles on a baking sheet lined with parchment paper.",
                    "Brush the puff pastry triangles with the beaten egg.",
                    "Bake for 20-25 minutes, or until the puff pastry triangles are golden and the apple mixture is bubbly.",
                    "Allow to cool completely before serving."
                )
            ),

            Receta(
                id = "14",
                categoria = "Snacks",
                titulo = "Cheese Stuffed Pretzel Bites",
                imagen = R.drawable.cheesepretzelbites,
                descripcion = "Savory pretzel bites filled with creamy cheese, perfect for dipping.",
                ingredientes = listOf(
                    Pair("Active dry yeast", "1 packet"),
                    Pair("Warm water", "1 1/2 cups"),
                    Pair("Sugar", "1 tablespoon"),
                    Pair("Salt", "2 teaspoons"),
                    Pair("All-purpose flour", "4 1/2 cups"),
                    Pair("Cream cheese, softened", "1 cup"),
                    Pair("Egg, beaten", "1"),
                    Pair("Coarse sea salt", "for sprinkling")
                ),
                pasos = listOf(
                    "Dissolve yeast in warm water in a bowl. Add sugar and let it sit for 5 minutes.",
                    "Mix in the salt and flour to form a dough.",
                    "Knead the dough on a floured surface until smooth.",
                    "Divide the dough into small balls and flatten each one.",
                    "Place a spoonful of cream cheese in the center and seal the dough around it.",
                    "Place the stuffed pretzel bites on a parchment-lined baking sheet.",
                    "Brush each pretzel bite with beaten egg and sprinkle with coarse salt.",
                    "Bake in a preheated oven at 425 degrees F (220 degrees C) for 12-15 minutes or until golden brown.",
                    "Serve warm with your favorite dip."
                )
            ),

            //Vegan
            Receta(
                id = "15",
                categoria = "Vegan",
                titulo = "Caprese Salad",
                imagen = R.drawable.ensaladacaprese,
                descripcion = "A fresh and delicious salad that is perfect for summer.",
                ingredientes = listOf(
                    Pair("Sliced tomatoes", "2 cups"),
                    Pair("Fresh mozarella, sliced", "1 cup"),
                    Pair("Fresh basil, chopped", "1/4 cup"),
                    Pair("Extra virgin olive oil", "2 tablespoons"),
                    Pair("Balsamic vinegar", "1 tablespoon"),
                    Pair("Salt and pepper", "to taste")
                ),
                pasos = listOf(
                    "On a plate, place the tomato and mozzarella slices.",
                    "Sprinkle with chopped basil.",
                    "Season with extra virgin olive oil, balsamic vinegar, salt, and pepper.",
                    "Serve immediately."
                )
            ),

            Receta(
                id = "16",
                categoria = "Vegan",
                titulo = "Quinoa and Black Bean Salad",
                imagen = R.drawable.quinoablackbeansalad,
                descripcion = "A hearty and protein-packed salad, perfect for a nutritious lunch.",
                ingredientes = listOf(
                    Pair("Cooked quinoa", "2 cups"),
                    Pair("Black beans, drained and rinsed", "1 cup"),
                    Pair("Corn kernels", "1/2 cup"),
                    Pair("Red bell pepper, diced", "1"),
                    Pair("Fresh cilantro, chopped", "1/4 cup"),
                    Pair("Lime juice", "3 tablespoons"),
                    Pair("Extra virgin olive oil", "2 tablespoons"),
                    Pair("Salt and pepper", "to taste")
                ),
                pasos = listOf(
                    "In a large bowl, combine quinoa, black beans, corn, and red bell pepper.",
                    "In a small bowl, whisk together lime juice, olive oil, salt, and pepper.",
                    "Pour the dressing over the salad and toss to combine.",
                    "Garnish with chopped cilantro.",
                    "Serve chilled or at room temperature."
                )
            ),

            Receta(
                id = "17",
                categoria = "Vegan",
                titulo = "Spicy Chickpea Wraps",
                imagen = R.drawable.spicychickpeawraps,
                descripcion = "Flavorful chickpea wraps with a spicy kick, perfect for a quick meal.",
                ingredientes = listOf(
                    Pair("Canned chickpeas, drained and rinsed", "1 cup"),
                    Pair("Tahini", "2 tablespoons"),
                    Pair("Lemon juice", "1 tablespoon"),
                    Pair("Garlic, minced", "1 clove"),
                    Pair("Smoked paprika", "1/2 teaspoon"),
                    Pair("Cayenne pepper", "1/4 teaspoon"),
                    Pair("Salt", "to taste"),
                    Pair("Whole wheat wraps", "4"),
                    Pair("Sliced cucumber", "1 cup"),
                    Pair("Sliced red onion", "1/2 cup")
                ),
                pasos = listOf(
                    "In a bowl, mash the chickpeas using a fork until they have a chunky texture.",
                    "Add tahini, lemon juice, garlic, smoked paprika, cayenne pepper, and salt. Mix well.",
                    "Lay out a whole wheat wrap and spread a generous amount of the chickpea mixture in the center.",
                    "Top with cucumber slices and red onion.",
                    "Fold the wrap and serve."
                )
            ),

            Receta(
                id = "18",
                categoria = "Pasta",
                titulo = "Fetuccini Alfredo",
                imagen = R.drawable.fetuccinialfredo,
                descripcion = "A creamy and delicious pasta that is perfect for any occasion.",
                ingredientes = listOf(
                    Pair("Fetuccini", "1 package (16 ounces)"),
                    Pair("Butter", "1/4 cup"),
                    Pair("Flour", "1/4 cup"),
                    Pair("Heavy cream", "1 cup"),
                    Pair("Grated parmesan cheese", "1/2 cup"),
                    Pair("Salt and pepper", "to taste")
                ),
                pasos = listOf(
                    "Cook the fettuccini according to package instructions.",
                    "Meanwhile, melt the butter in a saucepan over medium heat.",
                    "Add the flour and cook for 1 minute, stirring constantly.",
                    "Pour in the heavy cream and cook, stirring constantly, until the sauce thickens.",
                    "Add the grated parmesan cheese, salt, and pepper.",
                    "Serve the sauce over the cooked fettuccini."
                )
            ),

            Receta(
                id = "19",
                categoria = "Pasta",
                titulo = "Spaghetti Carbonara",
                imagen = R.drawable.spaghetticarbonara,
                descripcion = "A classic Roman dish made with eggs, cheese, pancetta, and pepper.",
                ingredientes = listOf(
                    Pair("Spaghetti", "1 package (16 ounces)"),
                    Pair("Pancetta or bacon, diced", "1/2 cup"),
                    Pair("Eggs", "2"),
                    Pair("Grated Pecorino Romano cheese", "1 cup"),
                    Pair("Grated Parmesan cheese", "1/2 cup"),
                    Pair("Black pepper", "1 teaspoon"),
                    Pair("Salt", "to taste")
                ),
                pasos = listOf(
                    "Cook the spaghetti according to package instructions.",
                    "While the pasta is cooking, fry the pancetta or bacon until crisp.",
                    "In a bowl, whisk together the eggs, Pecorino Romano, Parmesan, and black pepper.",
                    "Once the pasta is done, save 1 cup of pasta water and then drain the pasta.",
                    "Add the hot pasta to the pancetta and then quickly add the egg mixture.",
                    "Toss everything together, adding pasta water as needed to make a creamy sauce.",
                    "Serve immediately with more cheese and black pepper."
                )
            ),

            Receta(
                id = "20",
                categoria = "Pasta",
                titulo = "Linguine with Pesto and Sun-dried Tomatoes",
                imagen = R.drawable.linguinepesto,
                descripcion = "A vibrant and aromatic pasta dish featuring fresh basil pesto and sun-dried tomatoes.",
                ingredientes = listOf(
                    Pair("Linguine", "1 package (16 ounces)"),
                    Pair("Fresh basil pesto", "1/2 cup"),
                    Pair("Sun-dried tomatoes, chopped", "1/2 cup"),
                    Pair("Pine nuts, toasted", "1/4 cup"),
                    Pair("Grated Parmesan cheese", "1/2 cup"),
                    Pair("Olive oil", "2 tablespoons"),
                    Pair("Salt and pepper", "to taste")
                ),
                pasos = listOf(
                    "Cook the linguine according to package instructions.",
                    "In a large skillet, heat the olive oil over medium heat. Add the sun-dried tomatoes and sauté for 1-2 minutes.",
                    "Drain the linguine, reserving a cup of pasta water, and add the pasta to the skillet.",
                    "Stir in the fresh basil pesto, adding pasta water as needed to achieve the desired consistency for the sauce.",
                    "Toss everything together ensuring the pasta is well-coated with the pesto.",
                    "Serve hot, garnished with toasted pine nuts and grated Parmesan cheese."
                )
            ),
        )
    }
    fun toggleFavorite(recetaId: String): Boolean { //Método para el icono de bookmark_added, cuando el usuario desea guardar la receta
        val currentFavorites = _favoriteRecipes.value
        var wasAdded= false
        if (currentFavorites != null) {
            if (currentFavorites.contains(recetaId)) {
                _favoriteRecipes.value = currentFavorites - recetaId
            } else {
                _favoriteRecipes.value = currentFavorites + recetaId
                wasAdded=true
            }
        }
        saveFavoritesToSharedPreferences()
        return wasAdded
    }

    private fun saveFavoritesToSharedPreferences() { //Guarda la receta en un sharedPreference
        with(sharedPreferences.edit()) {
            putStringSet("favoriteRecipes", _favoriteRecipes.value)
            apply()
        }
    }

    private fun loadFavoritesFromSharedPreferences() { //Carga las recetas que fueron guardadas
        _favoriteRecipes.value = sharedPreferences.getStringSet("favoriteRecipes", emptySet()) ?: emptySet()
    }
}