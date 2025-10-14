package com.sopt.dive.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun Textfield (
    fieldName: String,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    Column (
        modifier = modifier.padding(vertical = 16.dp)
    ){
        Text(
            text = fieldName,
            fontSize = 30.sp,
            style = MaterialTheme.typography.titleMedium
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            placeholder = { "${placeholder} 입력해주세요" },
            modifier = modifier
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextfieldPreview() {
    DiveTheme {
        Textfield("ID","아이디를")
    }
}