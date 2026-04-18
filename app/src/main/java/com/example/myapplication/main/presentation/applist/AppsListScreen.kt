package com.example.myapplication.main.presentation.applist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.main.domain.AppItem
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun AppsListScreen(
    onItemClick: (AppItem) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppsListViewModel = viewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val localContext = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is AppsListEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        localContext.getString(event.messageResId)
                    )
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(Color(0xFF3F6DF6))
                    .clickable { viewModel.onLogoClick() }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "RuStore",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }

            AppsLazyList(
                itemsList = state.items,
                onItemClick = onItemClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppsListScreen() {
    MyApplicationTheme {
        AppsListScreen(
            onItemClick = {}
        )
    }
}