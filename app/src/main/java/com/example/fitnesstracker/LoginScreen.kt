package com.example.fitnesstracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun LoginScreen(
    onBackClick: () -> Unit = {}
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                    value = login,
                    onValueChange = { login = it },
                    label = stringResource(R.string.registration_screen_login),
                    hint = stringResource(R.string.registration_screen_login)
                )

                PasswordTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = stringResource(R.string.registration_screen_password),
                    hint = stringResource(R.string.registration_screen_password),
                )

                // Кнопка входа
                ButtonClassic(
                    modifier = Modifier,
                    text = stringResource(R.string.login_screen_to_enter)
                )
            }
        }
    )
}