package com.filkom.mycv2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class LastAction { LOGIN, DAFTAR }

data class AuthState(
    val nim: String = "",
    val nama: String = "",
    val email: String = "",
    val alamat: String = "",
    val lastAction: LastAction? = null
)

class AuthViewModel : ViewModel() {
    var uiState by mutableStateOf(AuthState())
        private set

    fun login(email: String) {
        uiState = uiState.copy(
            email = email,
            lastAction = LastAction.LOGIN
        )
    }

    fun daftar(nim: String, nama: String, email: String, alamat: String) {
        uiState = AuthState(
            nim = nim,
            nama = nama,
            email = email,
            alamat = alamat,
            lastAction = LastAction.DAFTAR
        )
    }
}
