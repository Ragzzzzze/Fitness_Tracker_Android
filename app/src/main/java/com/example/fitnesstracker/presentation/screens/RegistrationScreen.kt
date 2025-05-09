package com.example.fitnesstracker.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.presentation.ui.components.ButtonClassic
import com.example.fitnesstracker.presentation.ui.components.PasswordTextField
import com.example.fitnesstracker.presentation.ui.components.TextField
import com.example.fitnesstracker.presentation.ui.components.TermsAndPolicyText
import com.example.fitnesstracker.presentation.ui.components.TopBarExit
import com.example.fitnesstracker.presentation.ui.theme.Black
import com.example.fitnesstracker.presentation.ui.theme.Primary
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.state.RegistrationUiEvent
import com.example.fitnesstracker.presentation.viewmodels.RegistrationViewModel


@Composable
@Preview
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel(),
    onRegistrationSuccess: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val state by viewModel.uiState.collectAsState()

    val genders = listOf(
        stringResource(R.string.male),
        stringResource(R.string.female),
        stringResource(R.string.other))

    LaunchedEffect(state.registrationSuccess) {
        if (state.registrationSuccess) {
            onRegistrationSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopBarExit(
                text = stringResource(R.string.registration_screen_bar),
                onClick = onBackClick
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(padding)
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextField(
                    value = state.login,
                    onValueChange = { viewModel.sendEvent(RegistrationUiEvent.LoginChanged(it)) },

                    label = stringResource(R.string.registration_screen_login),
                    hint = stringResource(R.string.registration_screen_login)
                )

                TextField(
                    value = state.name,
                    onValueChange = { viewModel.sendEvent(RegistrationUiEvent.NameChanged(it)) },

                    label = stringResource(R.string.registration_screen_nickname),
                    hint = stringResource(R.string.registration_screen_nickname)
                )

                PasswordTextField(
                    value = state.password,
                    onValueChange = { viewModel.sendEvent(RegistrationUiEvent.PasswordChanged(it)) },

                    label = stringResource(R.string.registration_screen_password),
                    hint = stringResource(R.string.registration_screen_password),
                )

                PasswordTextField(
                    value = state.repeatedPassword,
                    onValueChange = { viewModel.sendEvent(RegistrationUiEvent.RepeatedPasswordChanged(it)) },

                    label = stringResource(R.string.registration_screen_password_second),
                    hint = stringResource(R.string.registration_screen_password_second),
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp), ) {
                    Text(
                        text = "Пол",
                        color = Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )


                    Column (
                        verticalArrangement = Arrangement.spacedBy((-8).dp)
                    ) {
                        genders.forEach { gender ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {  viewModel.sendEvent(RegistrationUiEvent.GenderChanged(gender)) }
                                    .offset(x = (-10).dp)
                            ) {
                                RadioButton(
                                    selected = (gender == state.gender),
                                    onClick = { viewModel.sendEvent(RegistrationUiEvent.GenderChanged(gender)) },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Primary
                                    )
                                )
                                Text(
                                    modifier = Modifier.padding(start = 6.dp),
                                    text = gender,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W400,
                                    letterSpacing = 0.sp,
                                )
                            }
                        }
                    }

                    ButtonClassic(
                        modifier = Modifier,
                        text = stringResource(R.string.welcome_screen_button),
                        onClick = { viewModel.sendEvent(RegistrationUiEvent.Submit) }
                    )

                    TermsAndPolicyText()

                    state.error?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                }
            }
        }
    )
}
