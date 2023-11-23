package com.example.proyectofinalmp.ui.misrecetas.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proyectofinalmp.ui.repository.Receta
import com.example.proyectofinalmp.ui.repository.RecetasRepository

class MyRecipesViewModel(application: Application) : AndroidViewModel(application) {
    private val recetasRepository = RecetasRepository(application)
    private val _recetas = MutableLiveData<List<Receta>>()
    val recetas: MutableLiveData<List<Receta>> = _recetas

    private val sharedPreferences = application.getSharedPreferences("RecetasFavorites", Context.MODE_PRIVATE)
    private val _favoriteRecipes = MutableLiveData<Set<String>>(emptySet())
    val favoriteRecipes: LiveData<Set<String>> get() = _favoriteRecipes //Obtener la lista de recetas que fueron guardadas como favoritas

    init {
        _recetas.value = recetasRepository.cargarRecetas(getApplication<Application>().applicationContext) //Cargar las recetas desde el repositorio de recetas
        loadFavoritesFromSharedPreferences()
    }

    private fun loadFavoritesFromSharedPreferences() { //Carga las recetas que fueron guardadas
        _favoriteRecipes.value = sharedPreferences.getStringSet("favoriteRecipes", emptySet()) ?: emptySet()
    }
}


