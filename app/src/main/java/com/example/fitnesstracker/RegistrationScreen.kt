package com.example.fitnesstracker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.fitnesstracker.ui.theme.Black
import com.example.fitnesstracker.ui.theme.Primary


@Composable
@Preview
fun RegistrationScreen(
    onBackClick: () -> Unit = {}
) {
    var login by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordSecond by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf<String?>(null) }
    val genders = listOf(
        stringResource(R.string.male),
        stringResource(R.string.female),
        stringResource(R.string.other))

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
                    value = login,
                    onValueChange = { login = it },
                    label = stringResource(R.string.registration_screen_login),
                    hint = stringResource(R.string.registration_screen_login)
                )

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = stringResource(R.string.registration_screen_nickname),
                    hint = stringResource(R.string.registration_screen_nickname)
                )

                PasswordTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = stringResource(R.string.registration_screen_password),
                    hint = stringResource(R.string.registration_screen_password),
                )

                PasswordTextField(
                    value = passwordSecond,
                    onValueChange = { passwordSecond = it },
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
                                    .clickable { selectedGender = gender }
                                    .offset(x = (-10).dp)
                            ) {
                                RadioButton(
                                    selected = (gender == selectedGender),
                                    onClick = { selectedGender = gender },
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
                        text = stringResource(R.string.welcome_screen_button)
                    )

                    TermsAndPolicyText()

                }
            }
        }
    )
}