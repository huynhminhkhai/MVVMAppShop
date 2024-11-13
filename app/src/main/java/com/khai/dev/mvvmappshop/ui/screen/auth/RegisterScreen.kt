package com.khai.dev.mvvmappshop.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.khai.dev.mvvmappshop.R
import com.khai.dev.mvvmappshop.models.auth.UserModel
import com.khai.dev.mvvmappshop.ui.component.CustomButton
import com.khai.dev.mvvmappshop.ui.navigation.Screen_main
import com.khai.dev.mvvmappshop.viewmodel.UserViewModel

@Composable
fun RegisterScreen(mainNavigation: NavHostController,
                   userViewModel: UserViewModel = viewModel()
){
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Quan sát trạng thái đăng ký
    val registerStatus by userViewModel.registerStatus.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {
        // Tiêu đề và logo
        HeaderSection()

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .height(600.dp)
                .clip(RoundedCornerShape(16.dp))
                .shadow(elevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp), verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Các trường nhập thông tin
                TextFieldSection("Name", name) { name = it }
                TextFieldSection("Email", email) { email = it }
                PasswordFieldSection("Password", password, passwordVisible) {
                    password = it
                }
                PasswordFieldSection("Confirm Password", confirmPassword, passwordVisible) {
                    confirmPassword = it
                }

                // Nút đăng ký
                CustomButton(
                    text = "Register",
                    onClick = {
                        val userModel = UserModel(name, password, confirmPassword, email)
                        userViewModel.register(userModel)
                    }
                )

                // Nút chuyển đến màn hình đăng nhập nếu đã có tài khoản
                CustomButton(
                    text = "Already have account? SIGN IN",
                    onClick = { mainNavigation.navigate(Screen_main.login.name) },
                    backgroundColor = Color.White,
                    textColor = Color(0xFF909090),
                    fontSize = 15.sp
                )

                // Hiển thị kết quả đăng ký
                registerStatus?.let {
                    when {
                        it.isSuccess -> {
                            Text("Registration successful!", color = Color.Green)
                            LaunchedEffect(Unit) {
                                mainNavigation.navigate(Screen_main.home.name)
                            }
                        }
                        it.isFailure -> Text("Registration failed: ${it.exceptionOrNull()?.message}", color = Color.Red)
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderSection() {
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
}

@Composable
fun TextFieldSection(label: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(
            text = label,
            color = Color(0xff909090),
            fontFamily = FontFamily(Font(R.font.nunitosans_7pt_condensed_light)),
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color("#E0E0E0".toColorInt()),
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.Gray,
                unfocusedIndicatorColor = Color.Gray,
            ),
        )
    }
}

@Composable
fun PasswordFieldSection(label: String, value: String, passwordVisible: Boolean, onValueChange: (String) -> Unit) {
    var localPasswordVisible by remember { mutableStateOf(passwordVisible) }
    Column {
        Text(
            text = label,
            color = Color(0xff909090),
            fontFamily = FontFamily(Font(R.font.nunitosans_7pt_condensed_light)),
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color("#E0E0E0".toColorInt()),
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.Gray,
                unfocusedIndicatorColor = Color.Gray,
            ),
            visualTransformation = if (localPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (localPasswordVisible)
                    painterResource(id = R.drawable.hide)
                else
                    painterResource(id = R.drawable.view)
                IconButton(onClick = { localPasswordVisible = !localPasswordVisible }) {
                    Icon(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        )
    }
}
