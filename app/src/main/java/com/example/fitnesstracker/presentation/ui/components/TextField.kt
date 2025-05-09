package com.example.fitnesstracker.presentation.ui.components


import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnesstracker.R
import com.example.fitnesstracker.presentation.ui.theme.Grey
import com.example.fitnesstracker.presentation.ui.theme.Primary

//TODO(strings to xml)

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    label: String = "example",
    hint: String = "example",
    value: String,
    onValueChange: (String) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    OutlinedTextField(
        modifier = Modifier
            .width(360.dp)
            .padding(bottom = 11.dp),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = if (isFocused || value.isNotEmpty()) Primary else Grey,
                letterSpacing = 0.sp,
            ) },
        placeholder = {
            Text(
                text = hint,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = Grey,
                letterSpacing = 0.sp,
            ) },
        interactionSource = interactionSource,
        maxLines = 1
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "example",
    hint: String = "example",
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = if (isFocused || value.isNotEmpty()) Primary else Grey,
                letterSpacing = 0.sp,
            ) },
        placeholder = {
            Text(
                text = hint,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = Grey,
                letterSpacing = 0.sp,
            ) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = painterResource(
                        id = if (passwordVisible) R.drawable.ic_eye_off
                        else R.drawable.ic_eye_on
                    ),
                    contentDescription = if (passwordVisible) "Скрыть пароль"
                    else "Показать пароль",
                )
            }
        },
        modifier = modifier
            .width(360.dp)
            .padding(bottom = 11.dp),
        interactionSource = interactionSource,
        maxLines = 1
    )
}