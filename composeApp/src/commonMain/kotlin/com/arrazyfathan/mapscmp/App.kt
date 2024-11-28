package com.arrazyfathan.mapscmp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arrazyfathan.mapscmp.network.NetworkRepository
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {

        var result by remember { mutableStateOf("") }
        val networkRepository = remember { NetworkRepository() }
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = {
                scope.launch {
                    result = networkRepository.getUsers()
                        .data?.firstOrNull()?.let {
                            "${it.firstName} ${it.lastName}"
                        }.orEmpty()
                }
            }) {
                Text("Test Api")
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text("Result : $result")
        }
    }
}