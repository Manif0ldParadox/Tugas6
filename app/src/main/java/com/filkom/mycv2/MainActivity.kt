package com.filkom.mycv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filkom.mycv2.screen.Login
import com.filkom.mycv2.screen.daftar
import com.filkom.mycv2.screen.detail
import com.filkom.mycv2.ui.theme.MyCV2Theme
import com.filkom.mycv2.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCV2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNav()
                }
            }
        }
    }
}

@Composable
fun AppNav() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            Login(
                onLogin = { email ->
                    authViewModel.login(email)
                    navController.navigate("detail")
                },
                onDaftar = { navController.navigate("daftar") }
            )
        }
        composable("daftar") {
            daftar(
                onSimpan = { nim, nama, email, alamat ->
                    authViewModel.daftar(nim, nama, email, alamat)
                    navController.navigate("detail")
                }
            )
        }
        composable("detail") {
            val state = authViewModel.uiState
            detail(
                state = state,
                onDaftar = { navController.navigate("daftar") }
            )
        }
    }
}
