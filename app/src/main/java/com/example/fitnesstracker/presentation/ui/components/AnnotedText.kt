package com.example.fitnesstracker.presentation.ui.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.presentation.ui.theme.Primary
import com.example.fitnesstracker.res.AppStrings.Companion


@Composable
@Preview
fun TermsAndPolicyText() {
    val uriHandler = LocalUriHandler.current

    val annotatedText = buildAnnotatedString {
        append(Companion.REGISTRATION_SCREEN_1ST_PART)
        append(" ")

        pushStringAnnotation(
            tag = "PRIVACY_POLICY",
            annotation = "https://example.com"
        )
        withStyle(
            style = SpanStyle(
                color = Primary,
            )
        ) {
            append(Companion.REGISTRATION_SCREEN_PRIVACY_POLICY)
        }
        pop()

        append(" ")
        append(Companion.REGISTRATION_SCREEN_2ND_PART)
        append(" ")

        pushStringAnnotation(
            tag = "TERMS_OF_USE",
            annotation = "https://example.com"
        )
        withStyle(
            style = SpanStyle(
                color = Primary
            )
        ) {
            append(Companion.REGISTRATION_SCREEN_TERMS_OF_USE)
        }
        pop()
        append(".")
    }

    ClickableText(
        text = annotatedText,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center,
        ),
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "PRIVACY_POLICY",
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                uriHandler.openUri(it.item)
            }

            annotatedText.getStringAnnotations(
                tag = "TERMS_OF_USE",
                start = offset,
                end = offset
            ).firstOrNull()?.let { uriHandler.openUri(it.item) }
        },
        modifier = Modifier

    )
}