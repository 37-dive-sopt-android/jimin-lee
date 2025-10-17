package com.sopt.dive.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField (
    fieldName: String,
    placeholder: String,
    text: String,
    onTextChange: (String) -> Unit,
    error: String,
    modifier: Modifier = Modifier,
) {
    var showPW by rememberSaveable { mutableStateOf(false) }

    Column (
        modifier = modifier.padding(vertical = 10.dp)
    ){
        Text(
            text = fieldName,
            fontSize = 30.sp,
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
                    text = "${placeholder} 입력해주세요",
                    color = Color.Gray
                )
            },
            visualTransformation =
                if(fieldName == "PW") {
                    if (!showPW) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    }
                }else {
                    VisualTransformation.None
                },
            trailingIcon = {
                if (fieldName == "PW") {
                    if (showPW) {
                        IconButton(onClick = { showPW = !showPW }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hidePW"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPW = !showPW }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "showPW"
                            )
                        }
                    }
                } else {
                }
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = error,
            fontSize = 15.sp,
            color = Color.Red
        )
    }
}

/*@Preview(showBackground = true)
@Composable
fun TextfieldPreview() {
    DiveTheme {
        CustomTextField("ID","아이디를", "input", {"input" = it})
    }
}*/