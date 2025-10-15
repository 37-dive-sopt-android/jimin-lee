package com.sopt.dive.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun CustomButton (
    buttonName: String,
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
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


@Composable
fun LoginBtn(
    onClick: () -> Unit
){
    CustomButton(
        buttonName = "Welcome To SOPT",
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        onClick = onClick
    )
}

@Composable
fun SignupBtn(
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
){
    CustomButton(
        buttonName = "회원가입하기",
        modifier = Modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun LoginButtonPreview() {
    DiveTheme {
        LoginBtn({})
    }
}

@Preview(showBackground = true)
@Composable
fun SignupButtonPreview() {
    DiveTheme {
        SignupBtn(Color.Transparent, Color.Gray, {})
    }
}