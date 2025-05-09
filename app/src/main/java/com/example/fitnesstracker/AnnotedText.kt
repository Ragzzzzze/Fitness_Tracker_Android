package com.example.fitnesstracker

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.ui.theme.Primary


@Composable
@Preview
fun TermsAndPolicyText() {
    val uriHandler = LocalUriHandler.current

    val annotatedText = buildAnnotatedString {
        append(stringResource(R.string.registration_screen_1st_part))
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
            append(stringResource(R.string.registration_screen_privacy_policy))
        }
        pop()

        append(" ")
        append(stringResource(R.string.registration_screen_2nd_part))
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
            append(stringResource(R.string.registration_screen_terms_of_use))
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