package com.example.fitnesstracker.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.presentation.ui.components.ButtonClassic
import com.example.fitnesstracker.presentation.ui.components.PasswordTextField
import com.example.fitnesstracker.presentation.ui.components.TextField
import com.example.fitnesstracker.presentation.ui.components.TopBarExit
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.state.LoginUiEvent
import com.example.fitnesstracker.presentation.viewmodels.LoginViewModel


@Composable
@Preview
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.loginSuccess) {
        if (state.loginSuccess) {
            onLoginSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopBarExit(
                text = stringResource(R.string.login_screen_enter),
                onClick = onBackClick
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.welcome_image),
                    contentDescription = "Welcome image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    contentScale = ContentScale.Crop
                )

                TextField(
                    value = state.login,
                    onValueChange = { viewModel.sendEvent(LoginUiEvent.LoginChanged(it)) },
                    label = stringResource(R.string.registration_screen_login),
                    hint = stringResource(R.string.registration_screen_login)
                )

                PasswordTextField(
                    value = state.password,
                    onValueChange = { viewModel.sendEvent(LoginUiEvent.PasswordChanged(it)) },
                    label = stringResource(R.string.registration_screen_password),
                    hint = stringResource(R.string.registration_screen_password),
                )

                ButtonClassic(
                    modifier = Modifier,
                    text = stringResource(R.string.login_screen_to_enter),
                    onClick = { viewModel.sendEvent(LoginUiEvent.Submit) }
                )

                state.error?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    )
}
