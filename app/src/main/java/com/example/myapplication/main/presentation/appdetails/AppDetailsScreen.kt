package com.example.myapplication.main.presentation.appdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import androidx.hilt.navigation.compose.hiltViewModel
@Composable
fun AppDetailsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: AppDetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val localContext = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is AppDetailsEvent.ShowSnackbar -> {
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
        val app = state.app

        if (app == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.no_app_details))
            }
        } else {
            Column(Modifier.padding(padding)) {
                Toolbar(
                    onBackClick = onBackClick,
                    onShareClick = viewModel::onLogoClick,
                )

                Spacer(Modifier.height(8.dp))

                AppDetailsHeader(
                    appDetails = app,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onLogoClick = viewModel::onLogoClick,
                )

                Spacer(Modifier.height(16.dp))

                InstallButton(
                    onClick = viewModel::onLogoClick,
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
                    onClick = viewModel::onLogoClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                )
            }
        }
    }
}