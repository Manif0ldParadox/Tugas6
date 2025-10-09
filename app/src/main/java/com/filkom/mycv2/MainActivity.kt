package com.filkom.mycv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filkom.mycv2.screen.Login
import com.filkom.mycv2.screen.daftar
import com.filkom.mycv2.screen.detail
import com.filkom.mycv2.ui.theme.MyCV2Theme

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
    var nim by rememberSaveable { mutableStateOf("") }
    var nama by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var alamat by rememberSaveable { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            Login(
                onLogin = { nimInput, namaInput ->
                    nim = nimInput
                    nama = namaInput
                    email = email
                    alamat = alamat
                    navController.navigate("detail")
                },
                onDaftar = { navController.navigate("daftar") },
                initialNim = nim,
                initialNama = nama
            )
        }
        composable("daftar") {
            daftar(
                onSimpan = { nimInput, namaInput, emailInput, alamatInput ->
                    nim = nimInput
                    nama = namaInput
                    email = emailInput
                    alamat = alamatInput
                    navController.navigate("detail")
                },
                initialNim = nim,
                initialNama = nama,
                initialEmail = email,
                initialAlamat = alamat
            )
        }
        composable("detail") {
            detail(
                nim = nim.ifEmpty { "-" },
                nama = nama.ifEmpty { "-" },
                email = email.ifEmpty { "-" },
                alamat = alamat.ifEmpty { "-" },
                onDaftar = { navController.navigate("daftar") }
            )
        }
    }
}
