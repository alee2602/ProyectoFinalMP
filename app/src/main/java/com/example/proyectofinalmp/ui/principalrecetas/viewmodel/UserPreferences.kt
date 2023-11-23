package com.example.proyectofinalmp.ui.principalrecetas.viewmodel
import android.content.Context

class UserPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) //Instanciar sharedpreferences para guardar el estado del usuario

    fun setGuest(isGuest: Boolean) { //Método que permite guardar un booleano, dependiendo de si es un invitado o no
        with(sharedPreferences.edit()) {
            putBoolean(IS_GUEST_KEY, isGuest)
            apply()
        }
    }

    fun isGuest(): Boolean { //Devuelve el valor booleano asociado con la clave IS_GUEST_KEY para determinar si entra como guest o no
        return sharedPreferences.getBoolean(IS_GUEST_KEY, false)
    }

    companion object {
        private const val PREF_NAME = "user_preferences" //Nombre del archivo de preferencias
        private const val IS_GUEST_KEY = "is_guest" //Clave con la que se recupera
    }
}