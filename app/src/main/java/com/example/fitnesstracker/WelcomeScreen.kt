package com.example.fitnesstracker

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.ui.theme.FitnesstrackerTheme

@Composable
@Preview
fun WelcomeScreen (
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    FitnesstrackerTheme {
        Column(
            modifier = Modifier.fillMaxSize()
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
                LargeText(text = stringResource(R.string.welcome_screen_heading_welcome_text))
                GrayText(text = stringResource(R.string.welcome_screen_subHeading_welcome_text))
                ButtonClassic(
                    text = stringResource(R.string.welcome_screen_button),
                    horizontalPadding = 45,
                    onClick = onRegisterClick,
                )
                LinkButton(
                    text = stringResource(R.string.welcome_screen_linkbutton),
                    onClick = onLoginClick
                )
            }
        }
    }
}