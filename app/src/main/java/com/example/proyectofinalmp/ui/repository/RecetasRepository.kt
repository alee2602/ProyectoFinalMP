package com.example.proyectofinalmp.ui.repository

import android.app.Application
import android.content.Context
import com.example.proyectofinalmp.R

data class Receta(
    val id: String,
    val categoria: String,
    val titulo: String,
    val imagen: Int,
    val descripcion: String,
    val ingredientes:  List<Pair<String, String>>, //Lista de ingredientes en formato pair (ingrediente, medida)
    val pasos:List<String>, //Lista con todos los pasos
    val infoNutricional: InformacionNutricional,
    val alergenos: List<String>? //Para manejar valores null
)
data class InformacionNutricional(
    val calorias: String,
    val proteinas: String,
    val grasas: String,
    val carbohidratos: String)

class RecetasRepository(application: Application) {
    fun cargarRecetas(context: Context): List<Receta> {
     return listOf(
            // Chicken recipes
            //Receta1
            Receta(
                id = "1",
                categoria = (context.getString(R.string.category_chicken)),
                titulo = context.getString(R.string.recipe_1_title),
                imagen = R.drawable.grilledchickensalad,
                descripcion = context.getString(R.string.recipe_1_description),
                ingredientes = listOf(
                    Pair(context.getString(R.string.recipe_1_ingredient_1),context.getString(R.string.recipe_1_ingredientpar_1)),
                    Pair(context.getString(R.string.recipe_1_ingredient_2), context.getString(R.string.recipe_1_ingredientpar_2)),
                    Pair(context.getString(R.string.recipe_1_ingredient_3), context.getString(R.string.recipe_1_ingredientpar_3)),
                    Pair(context.getString(R.string.recipe_1_ingredient_4), context.getString(R.string.recipe_1_ingredientpar_4)),
                    Pair(context.getString(R.string.recipe_1_ingredient_4), context.getString(R.string.recipe_1_ingredientpar_5))
                ),
                pasos = listOf(
                    context.getString(R.string.recipe_1_step_1),
                    context.getString(R.string.recipe_1_step_2),
                    context.getString(R.string.recipe_1_step_3),
                    context.getString(R.string.recipe_1_step_4)
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "330 kcal",
                    proteinas= "62 g",
                    grasas= "6 g",
                    carbohidratos= "4 g"
                ),
                alergenos= null
            ),

            //Receta2
            Receta(
                id = "2",
                categoria = (context.getString(R.string.category_chicken)),
                titulo = context.getString(R.string.recipe_2_title),
                imagen = R.drawable.chickenstirfry,
                descripcion = context.getString(R.string.recipe_2_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "327 kcal",
                    proteinas= "35 g",
                    grasas= "12 g",
                    carbohidratos= "35 g"
                ),
                alergenos= listOf(context.getString(R.string.allergensesame), context.getString(R.string.allergensoy))
            ),

            //Receta3
            Receta(
                id = "3",
                categoria = (context.getString(R.string.category_chicken)),
                titulo = context.getString(R.string.recipe_3_title),
                imagen = R.drawable.chickencurry,
                descripcion = context.getString(R.string.recipe_3_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "450 kcal",
                    proteinas= "42 g",
                    grasas= "18 g",
                    carbohidratos= "45 g"
                ),
                alergenos= null
            ),

            //Receta 4
            Receta(
                id = "4",
                categoria = (context.getString(R.string.category_chicken)),
                titulo = context.getString(R.string.recipe_4_title),
                imagen = R.drawable.chickensoup,
                descripcion = context.getString(R.string.recipe_4_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "250 kcal",
                    proteinas= "30 g",
                    grasas= "10 g",
                    carbohidratos= "20 g"
                ),
                alergenos= null
            ),

            //Receta 5
            Receta(
                id = "5",
                categoria = (context.getString(R.string.category_chicken)),
                titulo = context.getString(R.string.recipe_5_title),
                imagen = R.drawable.chickenpiccata,
                descripcion = context.getString(R.string.recipe_5_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "425 kcal",
                    proteinas= "37 g",
                    grasas= "10 g",
                    carbohidratos= "20 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendairy), context.getString(R.string.allergengluten))
            ),

            //Meat Recipes

            Receta(
                id = "6",
                categoria = (context.getString(R.string.category_meat)),
                titulo = context.getString(R.string.recipe_6_title),
                imagen = R.drawable.beefsteak,
                descripcion = context.getString(R.string.recipe_6_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "470 kcal",
                    proteinas= "38 g",
                    grasas= "32 g",
                    carbohidratos= "10 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendairy), context.getString(R.string.allergensulfites))
            ),

            Receta(
                id = "7",
                categoria = (context.getString(R.string.category_meat)),
                titulo = context.getString(R.string.recipe_7_title),
                imagen = R.drawable.porkchops,
                descripcion = context.getString(R.string.recipe_7_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "450 kcal",
                    proteinas= "35 g",
                    grasas= "25 g",
                    carbohidratos= "30 g"
                ),
                alergenos= listOf(context.getString(R.string.allergensulfites))
            ),

            Receta(
                id = "8",
                categoria = (context.getString(R.string.category_meat)),
                titulo = context.getString(R.string.recipe_8_title),
                imagen = R.drawable.groundbeeftacos,
                descripcion = context.getString(R.string.recipe_8_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "293 kcal",
                    proteinas= "14 g",
                    grasas= "18 g",
                    carbohidratos= "15 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendairy), context.getString(R.string.allergengluten))
            ),

            Receta(
                id = "9",
                categoria = (context.getString(R.string.category_meat)),
                titulo = context.getString(R.string.recipe_9_title),
                imagen = R.drawable.beefstroganoff,
                descripcion = context.getString(R.string.recipe_9_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "520 kcal",
                    proteinas= "45 g",
                    grasas= "30 g",
                    carbohidratos= "55 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendairy), context.getString(R.string.allergengluten))
            ),

            Receta(
                id = "10",
                categoria = (context.getString(R.string.category_meat)),
                titulo = context.getString(R.string.recipe_10_title),
                imagen = R.drawable.beefbourguignon,
                descripcion = context.getString(R.string.recipe_10_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "611 kcal",
                    proteinas= "40 g",
                    grasas= "37 g",
                    carbohidratos= "59 g"
                ),
                alergenos= listOf(context.getString(R.string.allergengluten), context.getString(R.string.allergensulfites))
            ),

            //Snacks
            Receta(
                id = "11",
                categoria = (context.getString(R.string.category_snacks)),
                titulo = context.getString(R.string.recipe_11_title),
                imagen = R.drawable.yogurtparfait,
                descripcion = context.getString(R.string.recipe_11_description),
                ingredientes = listOf(
                    Pair(context.getString(R.string.recipe_11_ingredient_1), context.getString(R.string.recipe_11_ingredientpar_1)),
                    Pair(context.getString(R.string.recipe_11_ingredient_2), context.getString(R.string.recipe_11_ingredientpar_2)),
                    Pair(context.getString(R.string.recipe_11_ingredient_3), context.getString(R.string.recipe_11_ingredientpar_3))
                ),
                pasos = listOf(
                    context.getString(R.string.recipe_11_step_1),
                    context.getString(R.string.recipe_11_step_2),
                    context.getString(R.string.recipe_11_step_3)
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "230 kcal",
                    proteinas= "20 g",
                    grasas= "8 g",
                    carbohidratos= "55 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendairy),context.getString(R.string.allergengluten),context.getString(R.string.allergennuts))
            ),

            Receta(
                id = "12",
                categoria = (context.getString(R.string.category_snacks)),
                titulo = context.getString(R.string.recipe_12_title),
                imagen = R.drawable.trailmix,
                descripcion = context.getString(R.string.recipe_12_description),
                ingredientes = listOf(
                    Pair(context.getString(R.string.recipe_12_ingredient_1), context.getString(R.string.recipe_12_ingredientpar_1)),
                    Pair(context.getString(R.string.recipe_12_ingredient_2), context.getString(R.string.recipe_12_ingredientpar_2)),
                    Pair(context.getString(R.string.recipe_12_ingredient_3), context.getString(R.string.recipe_12_ingredientpar_3)),
                    Pair(context.getString(R.string.recipe_12_ingredient_4), context.getString(R.string.recipe_12_ingredientpar_4))
                ),
                pasos = listOf(
                    context.getString(R.string.recipe_12_step_1),
                    context.getString(R.string.recipe_12_step_1)
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "250 kcal",
                    proteinas= "15 g",
                    grasas= "45 g",
                    carbohidratos= "35 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendf), context.getString(R.string.allergennuts))
            ),

            Receta(
                id = "13",
                categoria = (context.getString(R.string.category_snacks)),
                titulo = context.getString(R.string.recipe_13_title),
                imagen = R.drawable.applestrudelbites,
                descripcion = context.getString(R.string.recipe_13_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "175 kcal",
                    proteinas= "4 g",
                    grasas= "10 g",
                    carbohidratos= "21 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendairy), context.getString(R.string.allergenegg), context.getString(R.string.allergengluten))
            ),

            Receta(
                id = "14",
                categoria = (context.getString(R.string.category_snacks)),
                titulo = context.getString(R.string.recipe_14_title),
                imagen = R.drawable.cheesepretzelbites,
                descripcion = context.getString(R.string.recipe_14_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "230 kcal",
                    proteinas= "10 g",
                    grasas= "12 g",
                    carbohidratos= "26 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendairy), context.getString(R.string.allergenegg), context.getString(R.string.allergengluten))
            ),

            //Vegan
            Receta(
                id = "15",
                categoria = (context.getString(R.string.category_vegan)),
                titulo = context.getString(R.string.recipe_15_title),
                imagen = R.drawable.ensaladacaprese,
                descripcion = context.getString(R.string.recipe_15_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "220 kcal",
                    proteinas= "15 g",
                    grasas= "14 g",
                    carbohidratos= "12 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendairy))
            ),

            Receta(
                id = "16",
                categoria = (context.getString(R.string.category_vegan)),
                titulo = context.getString(R.string.recipe_16_title),
                imagen = R.drawable.quinoablackbeansalad,
                descripcion = context.getString(R.string.recipe_16_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "480 kcal",
                    proteinas= "20 g",
                    grasas= "15 g",
                    carbohidratos= "70 g"
                ),
                alergenos= null
            ),

            Receta(
                id = "17",
                categoria = (context.getString(R.string.category_vegan)),
                titulo = context.getString(R.string.recipe_17_title),
                imagen = R.drawable.spicychickpeawraps,
                descripcion = context.getString(R.string.recipe_17_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "325 kcal",
                    proteinas= "15 g",
                    grasas= "12 g",
                    carbohidratos= "40 g"
                ),
                alergenos= listOf(context.getString(R.string.allergengluten), context.getString(R.string.allergensesame))
            ),

            Receta(
                id = "18",
                categoria = (context.getString(R.string.category_pasta)),
                titulo = context.getString(R.string.recipe_18_title),
                imagen = R.drawable.fetuccinialfredo,
                descripcion = context.getString(R.string.recipe_18_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "670 kcal",
                    proteinas= "36 g",
                    grasas= "62 g",
                    carbohidratos= "76 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendairy), context.getString(R.string.allergengluten))
            ),

            Receta(
                id = "19",
                categoria = (context.getString(R.string.category_pasta)),
                titulo = context.getString(R.string.recipe_19_title),
                imagen = R.drawable.spaghetticarbonara,
                descripcion = context.getString(R.string.recipe_19_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "778 kcal",
                    proteinas= "47 g",
                    grasas= "69 g",
                    carbohidratos= "74 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendairy), context.getString(R.string.allergengluten))
            ),

            Receta(
                id = "20",
                categoria = (context.getString(R.string.category_pasta)),
                titulo = context.getString(R.string.recipe_20_title),
                imagen = R.drawable.linguinepesto,
                descripcion = context.getString(R.string.recipe_20_description),
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
                ),
                infoNutricional = InformacionNutricional(
                    calorias= "830 kcal",
                    proteinas= "35 g",
                    grasas= "79 g",
                    carbohidratos= "82 g"
                ),
                alergenos= listOf(context.getString(R.string.allergendairy), context.getString(R.string.allergengluten), context.getString(R.string.allergennuts))
            ),
        )
    }
}