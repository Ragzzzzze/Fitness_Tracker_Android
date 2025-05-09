package com.example.fitnesstracker.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.presentation.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TopBarExit (
    modifier: Modifier = Modifier,
    text: String = "example",
    onClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
                letterSpacing = 0.sp,
            )
        },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Назад",
                    tint = Primary
                )
            }
        }
    )
}