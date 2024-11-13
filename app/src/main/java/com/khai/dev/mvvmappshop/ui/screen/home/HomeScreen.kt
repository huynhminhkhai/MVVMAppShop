package com.khai.dev.mvvmappshop.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.khai.dev.mvvmappshop.R
import com.khai.dev.mvvmappshop.models.home.CategoryModel
import com.khai.dev.mvvmappshop.models.home.ProductModel
import com.khai.dev.mvvmappshop.ui.navigation.Screen_main
import com.khai.dev.mvvmappshop.viewmodel.CategoryViewModel
import com.khai.dev.mvvmappshop.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    innerPadding: PaddingValues = PaddingValues(),
    mainNavController: NavController,
    categoryViewModel: CategoryViewModel = viewModel(),
    productViewModel: ProductViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp, end = 15.dp, start = 15.dp)
            .fillMaxHeight() // Đảm bảo chiếm toàn bộ chiều cao
    ) {
        // Quan sát danh sách danh mục từ ViewModel
        val categories by categoryViewModel.categories.observeAsState(initial = emptyList())
        var selectedCategoryId by remember { mutableLongStateOf(1L) } // Mặc định chọn danh mục đầu tiên

//         Lấy danh sách sản phẩm theo ID danh mục khi ID danh mục thay đổi
        LaunchedEffect(selectedCategoryId) {
            productViewModel.getProductsByCategoryId(selectedCategoryId)
        }

        val products by productViewModel.products.observeAsState(initial = emptyList())

        // Hiển thị danh sách danh mục
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
        ) {
            categories.forEach { category ->
                CategoryCardItem(
                    category = category,
                    isSelected = category.id == selectedCategoryId,
                    onClick = {
                        selectedCategoryId = category.id // Cập nhật ID danh mục được chọn
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Hiển thị danh sách sản phẩm theo danh mục đã chọn
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            items(products.chunked(2)) { rowProducts ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    rowProducts.forEach { product ->
                        ProductItem(product = product, mainNavController)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: ProductModel, mainNavController: NavController) {
    Column(
        modifier = Modifier
            .width(165.dp)
            .height(253.dp)
            .clickable {
                // Điều hướng sang ProductDetail với productId
                mainNavController.navigate("${Screen_main.detail.name}/${product.id}")
            },
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.fullImageUrl),
                modifier = Modifier.fillMaxSize(),
                contentDescription = product.name,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp, end = 15.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Row {
                    // Thêm các biểu tượng hoặc nhãn nếu cần
                }
                Row(
                    modifier = Modifier
                        .size(30.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(6.dp))
                        .background(Color("#95a5a6".toColorInt()))
                        .alpha(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.shopping_bag),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
        Text(
            text = product.name,
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight(500),
            fontFamily = FontFamily(Font(R.font.nunitosans_7pt_condensed_light))
        )
        Text(
            text = "$${product.price}",
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.nunitosans_7pt_condensed_light))
        )
    }
}

@Composable
fun CategoryCardItem(category: CategoryModel, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(end = 25.dp)
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .size(44.dp)
                .shadow(elevation = 2.dp, RoundedCornerShape(14.dp))
                .background(
                    color = if (isSelected) Color("#3d3d3d".toColorInt())
                    else Color("#FFFFFF".toColorInt())
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = rememberAsyncImagePainter(category.fullImageUrl),
                contentDescription = category.name,
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = category.name,
            color = Color("#999999".toColorInt()),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.nunitosans_7pt_condensed_light))
        )
    }
}
