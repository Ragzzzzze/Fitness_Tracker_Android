package com.example.fitnesstracker.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.presentation.ui.components.ButtonClassic
import com.example.fitnesstracker.presentation.ui.components.GrayText
import com.example.fitnesstracker.presentation.ui.components.LargeText
import com.example.fitnesstracker.presentation.ui.components.LinkButton
import com.example.fitnesstracker.presentation.ui.theme.FitnesstrackerTheme
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.ui.theme.White
import com.example.fitnesstracker.res.AppStrings.Companion

@Composable
@Preview
fun WelcomeScreen (
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    FitnesstrackerTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(White),
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Image(
                painter = painterResource(id = R.drawable.welcome_image),
                contentDescription = "Welcome image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                contentScale = ContentScale.Crop
            )

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LargeText(text = Companion.WELCOME_SCREEN_HEADING_WELCOME_TEXT)
                GrayText(text = Companion.WELCOME_SCREEN_SUBHEADING_WELCOME_TEXT)
                ButtonClassic(
                    text = Companion.WELCOME_SCREEN_BUTTON,
                    horizontalPadding = 45,
                    onClick = onRegisterClick,
                )
                LinkButton(
                    text = Companion.WELCOME_SCREEN_LINKBUTTON,
                    onClick = onLoginClick
                )
            }
        }
    }
}