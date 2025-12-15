package com.example.routeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.routeapp.ui.theme.White


@Composable
fun AuthField(
    label: String,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean= false
) {
    Column {
        Text(label, color = White, fontSize = 12.sp)
        Spacer(Modifier.height(4.dp))

        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(hint) },
            singleLine = true,
            visualTransformation =
                if (isPassword) PasswordVisualTransformation()
                else VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))
    }
}

