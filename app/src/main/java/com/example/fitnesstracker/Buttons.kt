package com.example.fitnesstracker

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.ui.theme.FitnesstrackerTheme
import com.example.fitnesstracker.ui.theme.Primary
import com.example.fitnesstracker.ui.theme.White

@Composable
@Preview
fun ButtonClassic (
    modifier: Modifier = Modifier,
    text: String = "example",
    horizontalPadding: Int = 0,
    onClick: () -> Unit = {}

) {
    FitnesstrackerTheme {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp, horizontal = horizontalPadding.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            ),
            onClick = onClick,
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                letterSpacing = 0.sp,
                color = White
            )
        }
    }
}

@Composable
@Preview
fun LinkButton (
    modifier: Modifier = Modifier,
    text: String = "example",
    onClick: () -> Unit = {},
) {
    FitnesstrackerTheme {
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            onClick = onClick
        ) {
            Text (
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                letterSpacing = 0.sp,
                color = Primary,
            )
        }
    }
}