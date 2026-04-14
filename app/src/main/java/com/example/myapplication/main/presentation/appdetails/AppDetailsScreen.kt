package com.example.myapplication.main.presentation.appdetails

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R

@Composable
fun AppDetailsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: AppDetailsViewModel = viewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    val snackbarHostState = remember { SnackbarHostState() }
    val underDevText = stringResource(R.string.under_developement)

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                is AppDetailsEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(Modifier.padding(padding)) {

            Toolbar(
                onBackClick = onBackClick,
                onShareClick = { viewModel.onLogoClick(underDevText) },
            )

            Spacer(Modifier.height(8.dp))

            val app = state.app
            if (app == null) {

                return@Column
            }

            AppDetailsHeader(
                appDetails = app,
                modifier = Modifier.padding(horizontal = 16.dp),
                onLogoClick = { viewModel.onLogoClick(underDevText) }
            )

            Spacer(Modifier.height(16.dp))

            InstallButton(
                onClick = { viewModel.onLogoClick(underDevText) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(12.dp))

            ScreenshotsList(
                screenshotUrlList = app.screenshotUrlList,
                contentPadding = PaddingValues(horizontal = 16.dp),
            )

            Spacer(Modifier.height(12.dp))

            AppDescription(
                description = app.description,
                collapsed = state.isDescriptionExpanded,
                onReadMoreClick = viewModel::onReadMoreClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )

            Spacer(Modifier.height(12.dp))

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.outlineVariant,
            )

            Spacer(Modifier.height(12.dp))

            Developer(
                name = app.developer,
                onClick = { viewModel.onLogoClick(underDevText) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
            )
        }
    }
}