package com.sopt.dive.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton (
    buttonName: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor, contentColor)
    ) {
        Text(
            text = buttonName,
            modifier = Modifier.padding(vertical = 5.dp),
            fontSize = 17.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomButtonPreview() {
    CustomButton("",Color.Gray,Color.Black,Modifier,{})
}