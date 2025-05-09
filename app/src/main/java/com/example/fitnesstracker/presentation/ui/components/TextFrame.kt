package com.example.fitnesstracker.presentation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.presentation.ui.theme.Black
import com.example.fitnesstracker.presentation.ui.theme.FitnesstrackerTheme
import com.example.fitnesstracker.presentation.ui.theme.Grey

@Composable
@Preview
fun LargeText(
    modifier: Modifier = Modifier,
    text: String = "example",

) {
    FitnesstrackerTheme {
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            letterSpacing = 0.sp,
            color = Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(22.dp))
    }
}

@Composable
@Preview
fun GrayText(

    modifier: Modifier = Modifier,
    text: String = "example",

    ) {
    FitnesstrackerTheme {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            letterSpacing = 0.sp,
            color = Grey,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}