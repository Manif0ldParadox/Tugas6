package com.filkom.mycv2.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.filkom.mycv2.viewmodel.AuthState
import com.filkom.mycv2.viewmodel.LastAction

@Composable
fun detail(
    state: AuthState,
    onDaftar: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Halaman Detail",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        Spacer(Modifier.height(16.dp))

        when (state.lastAction) {
            LastAction.LOGIN -> {
                Text("Anda login dengan email:")
                Text(state.email.ifEmpty { "-" })
            }
            LastAction.DAFTAR -> {
                Text("Data Pendaftaran:")
                Text("NIM   : ${state.nim.ifEmpty { "-" }}")
                Text("Nama  : ${state.nama.ifEmpty { "-" }}")
                Text("Email : ${state.email.ifEmpty { "-" }}")
                Text("Alamat: ${state.alamat.ifEmpty { "-" }}")
            }
            null -> {
                Text("Belum ada data.")
            }
        }

        Spacer(Modifier.height(16.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onDaftar
        ) {
            Text("DAFTAR")
        }
    }
}

@Preview
@Composable
fun detailPreview() {
    detail(state = AuthState())
}
