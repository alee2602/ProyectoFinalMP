package com.example.proyectofinalmp.ui.detailrecetas.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proyectofinalmp.ui.repository.Receta
import com.example.proyectofinalmp.ui.repository.RecetasRepository

class RecipeDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val recetasRepository = RecetasRepository(application)
    private val _recetas = MutableLiveData<List<Receta>>()
    val recetas: MutableLiveData<List<Receta>> = _recetas

    private val sharedPreferences = application.getSharedPreferences("RecetasFavorites", Context.MODE_PRIVATE)
    private val _favoriteRecipes = MutableLiveData<Set<String>>(emptySet())
    val favoriteRecipes: LiveData<Set<String>> get() = _favoriteRecipes

    init {
        _recetas.value = recetasRepository.cargarRecetas(application.applicationContext)
        loadFavorites()
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

    private fun loadFavorites() { //Carga las recetas que fueron guardadas
        _favoriteRecipes.value = sharedPreferences.getStringSet("favoriteRecipes", emptySet()) ?: emptySet()
    }

}
