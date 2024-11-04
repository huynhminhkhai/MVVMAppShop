package com.khai.dev.mvvmappshop.ui.screen.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.khai.dev.mvvmappshop.models.welcome.WelcomeModel
import com.khai.dev.mvvmappshop.viewmodel.WelcomeViewModel
import kotlin.math.log

@Composable
fun WelcomeScreen(welcomeViewModel: WelcomeViewModel = viewModel()) {
    // Quan sát LiveData từ ViewModel
    val welcomes by welcomeViewModel.welcomes.observeAsState(initial = emptyList())


    LazyColumn {
        items(welcomes) { welcomeItem ->
            WelcomeItem(welcomeItem)
        }
    }
}

@Composable
fun WelcomeItem(welcomeModel: WelcomeModel) {
//    val fullImageUrl = "http://172.16.106.205:1402/api/welcomes" + welcomeModel.imageUrl
    Log.d("LinkImage", "Đường dẫn ảnh: ${welcomeModel.fullImageUrl}")
    Text(text = welcomeModel.title)
    Text(text = welcomeModel.description)
    Image(
        painter = rememberAsyncImagePainter(welcomeModel.fullImageUrl),
        contentDescription = welcomeModel.description,
                modifier = Modifier.size(200.dp)
    )
    // Thêm các thành phần khác để hiển thị thông tin của `welcomeModel` nếu cần
}