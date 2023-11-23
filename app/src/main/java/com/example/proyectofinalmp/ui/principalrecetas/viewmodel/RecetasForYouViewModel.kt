package com.example.proyectofinalmp.ui.principalrecetas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyectofinalmp.navigation.model.BottomBarScreen
import com.example.proyectofinalmp.ui.repository.Receta
import com.example.proyectofinalmp.ui.repository.RecetasRepository

class AppViewModel : ViewModel() {
    var currentMainScreen: BottomBarScreen = BottomBarScreen.RecetasForYou // Inicializar con la pantalla predeterminada
        private set

    // Función para cambiar la pantalla principal actual
    fun setCurrentMainScreen(screen: BottomBarScreen) {
        currentMainScreen = screen
    }
}

class RecetasForYouViewModel(application: Application) : AndroidViewModel(application) {
    private val recetasRepository = RecetasRepository(application)
    private val _recetas = MutableLiveData<List<Receta>>()
    val recetas: MutableLiveData<List<Receta>> = _recetas

    init {
        cargarRecetas()
    }
    private fun cargarRecetas() {
        //Devuelve una lista con todas las recetas desde el repositorio
        recetas.value = recetasRepository.cargarRecetas(getApplication<Application>().applicationContext)
    }
}



