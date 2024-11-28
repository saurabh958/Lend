package com.example.lend.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldComposable(
    modifier: Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMessage: String,
    isError: Boolean,
    onChange: (String) -> Unit,
    value: String,
    placeholder: String
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 20.dp),
            value = value,
            singleLine = true,
            isError = isError,
            onValueChange = {
                onChange(it)
            },
            label = {
                Text(text = placeholder)
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 12.sp,
                fontFamily = FontFamily.Default
            ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, autoCorrect = false)
        )
        Spacer(
            modifier = modifier
                .padding(top = 10.dp)
        )
        if (isError) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 2.dp),
                textAlign = TextAlign.Start
            )
        }
    }
}