package com.khai.dev.mvvmappshop.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.khai.dev.mvvmappshop.R
import com.khai.dev.mvvmappshop.ui.component.CustomButton
import com.khai.dev.mvvmappshop.ui.navigation.Screen_main

@Preview
@Composable
fun LoginScreen(mainNavigation: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HorizontalDivider(
                    modifier = Modifier.width(105.dp),
                    thickness = 2.dp,
                    color = Color("#BDBDBD".toColorInt())
                )
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(75.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.width(105.dp),
                    thickness = 2.dp,
                    color = Color("#BDBDBD".toColorInt())
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Text(
                text = "Hello !",
                lineHeight = 50.sp,
                color = Color.Gray,
                fontSize = 27.sp,
                fontWeight = FontWeight(500),
                fontFamily = FontFamily(Font(R.font.merriweather_regular))
            )
            Text(
                text = "WELCOME BACK",
                lineHeight = 50.sp,
                color = Color.Black,
                fontSize = 27.sp,
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.merriweather_regular))
            )
        }
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(16.dp))
                .shadow(elevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp), verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var username by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var passwordVisible by remember { mutableStateOf(false) }
                Column {
                    Text(
                        text = "Email",
                        color = Color(0xff909090),
                        fontFamily = FontFamily(Font(R.font.nunitosans_7pt_condensed_light)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    TextField(
                        value = username,
                        onValueChange = {
                            username = it
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color("#E0E0E0".toColorInt()),
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Gray,
                        ),
                    )
                }
                Column {
                    Text(
                        text = "PassWord",
                        color = Color(0xff909090),
                        fontFamily = FontFamily(Font(R.font.nunitosans_7pt_condensed_light)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color("#E0E0E0".toColorInt()),
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Gray,
                        ),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image =
                                if (passwordVisible)
                                    painterResource(id = R.drawable.hide)
                                else
                                    painterResource(id = R.drawable.view)
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    painter = image,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    )
                }
                CustomButton(
                    text = "Forgot Password",
                    onClick = { /* Xử lý khi nhấn vào nút Sign up */ },
                    backgroundColor = Color.White,
                    textColor = Color(0xFF909090),
                    fontSize = 15.sp
                )
                CustomButton(
                    text = "Log in",
                    onClick = { /* Xử lý khi nhấn vào nút Log in */ }
                )
                CustomButton(
                    text = "SIGN UP",
                    onClick = { mainNavigation.navigate(Screen_main.register.name)},
                    backgroundColor = Color.White,
                    textColor = Color(0xFF303030)
                )

            }
        }
    }
}