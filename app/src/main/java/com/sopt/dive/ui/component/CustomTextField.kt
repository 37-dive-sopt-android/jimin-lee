package com.sopt.dive.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sopt.dive.R

@Composable
fun CustomTextField (
    fieldName: String,
    placeholder: String,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    error: String = ""
) {
    var showPW by rememberSaveable { mutableStateOf(false) }

    Column (
        modifier = modifier
    ){
        Text(
            text = fieldName,
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium
        )
        TextField(
            value = text,
            onValueChange = onTextChange,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
                cursorColor = Color.Black
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color.Gray
                )
            },
            visualTransformation =
                if(isPassword) {
                    if (!showPW) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    }
                }else {
                    VisualTransformation.None
                },
            trailingIcon = {
                if (isPassword) {
                    if (showPW) {
                        IconButton(onClick = { showPW = !showPW }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = stringResource(R.string.trailingIcon_hidePW)
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPW = !showPW }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = stringResource(R.string.trailingIcon_showpw)
                            )
                        }
                    }
                }
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = error,
            fontSize = 13.sp,
            color = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextfieldPreview() {
    CustomTextField("ID","아이디를", "input", { })
}