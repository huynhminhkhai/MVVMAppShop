package com.khai.dev.mvvmappshop.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.khai.dev.mvvmappshop.R
import com.khai.dev.mvvmappshop.models.auth.WelcomeModel
import com.khai.dev.mvvmappshop.ui.component.CustomButton
import com.khai.dev.mvvmappshop.ui.navigation.Screen_main
import com.khai.dev.mvvmappshop.viewmodel.WelcomeViewModel

@Composable
fun WelcomeScreen(
    mainNavController: NavHostController,
    welcomeViewModel: WelcomeViewModel = viewModel()
) {
    // Quan sát LiveData từ ViewModel
    val welcomes by welcomeViewModel.welcomes.observeAsState(initial = emptyList())

    // Lấy một welcome ngẫu nhiên
    val randomWelcome = welcomes.randomOrNull() // Trả về null nếu danh sách rỗng

    // Hiển thị welcome nếu có
    randomWelcome?.let { WelcomeItem(it, mainNavController) }
}


@Composable
fun WelcomeItem(welcomeModel: WelcomeModel, mainNavController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    ) {
        Image(
            painter = rememberAsyncImagePainter(welcomeModel.fullImageUrl),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(400.dp)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "MAKE YOUR",
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.gray),
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.gelasio_bold))
                )
                Text(
                    text = welcomeModel.title,
                    fontSize = 30.sp,
                    color = colorResource(id = R.color.black),
                    fontWeight = FontWeight(700),
                    fontFamily = FontFamily(Font(R.font.gelasio_bold))
                )
                Text(
                    modifier = Modifier.padding(start = 25.dp, top = 15.dp),
                    text = "The best simple place where you discover most wonderful furnitures and make your home beautiful",
                    color = colorResource(id = R.color.graySecond),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.nunitosans_7pt_condensed_light)),
                    lineHeight = 35.sp,
                    textAlign = TextAlign.Justify
                )
            }
            Column {
                CustomButton(
                    onClick = { mainNavController.navigate(Screen_main.login.name) },
                    text = "Get Started",
                    width = 160.dp,
                    height = 60.dp
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
