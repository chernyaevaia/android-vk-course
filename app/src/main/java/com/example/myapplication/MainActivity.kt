package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.core.net.toUri
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var input by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier) {
        TextField(
            value = input,
            onValueChange = {
                input = it
                errorText = null
            }
        )

        if (errorText != null) {
            Text(
                text = errorText!!,
                color = Color.Red
            )
        }

        Button(onClick = {
            val text = input.trim()
            if (text.isEmpty()) {
                errorText = "Введите текст"
                return@Button
            }

            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("text", text)
            context.startActivity(intent)
        }) {
            Text("Открыть вторую Activity")
        }
        Button(onClick = {
            val text = input.trim()
            if (text.isEmpty()) {
                errorText = "Введите текст"
                return@Button
            }

            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, text)
            }

            val chooser = Intent.createChooser(sendIntent, "Поделиться через…")
            context.startActivity(chooser)
        }) {
            Text("Поделиться текстом")
        }
        Button(onClick = {
            val text = input.trim()

            when {
                text.isEmpty() -> {
                    errorText = "Введите номер"
                    return@Button
                }
                text.none { it.isDigit() } -> {
                    errorText = "Введите номер"
                    return@Button
                }
                text.any { it.isLetter() } -> {
                    errorText = "Номер не должен содержать букв"
                    return@Button
                }
            }

            errorText = null

            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = "tel:$text".toUri()
            }
            context.startActivity(intent)
        }) {
            Text("Позвонить другу")
        }
    }
}