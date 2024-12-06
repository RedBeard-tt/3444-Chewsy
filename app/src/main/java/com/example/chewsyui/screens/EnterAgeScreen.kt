package com.example.chewsyui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun EnterAgeScreen() {
    var age by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Enter your age",
            fontSize = 18.sp,
            color = Color(0xFF0047AB)
        )

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = age,
            onValueChange = { age = it },
            modifier = Modifier
                .width(250.dp)
                .height(48.dp)
                .background(Color.Transparent),
            singleLine = true,
            textStyle = TextStyle(color = Color(0xFF0047AB), fontSize = 16.sp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .background(Color(0xFFC3EAF9))
                        .padding(4.dp)
                ) {
                    if (age.isEmpty()) {
                        Text(
                            text = "Enter your age",
                            color = Color(0xFFB3E5FC),
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Handle button click */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1)),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = "Submit",
                color = Color.White
            )
        }
    }
}
