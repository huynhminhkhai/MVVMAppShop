package com.khai.dev.mvvmappshop.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.khai.dev.mvvmappshop.R
import com.khai.dev.mvvmappshop.models.home.ProductModel
import com.khai.dev.mvvmappshop.ui.component.CustomButton
import com.khai.dev.mvvmappshop.viewmodel.ProductViewModel

@Composable
fun DetailProduct(
    mainNavController: NavController,
    productId: Long,
    productViewModel: ProductViewModel = viewModel()
) {
    // Lấy chi tiết sản phẩm khi `productId` thay đổi
    LaunchedEffect(productId) {
        productViewModel.getProductById(productId)
    }

    val productDetail by productViewModel.productDetail.observeAsState()

    productDetail?.let { product ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Hiển thị hình ảnh sản phẩm
            AsyncImage(
                model = product.fullImageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(390.dp)
                    .padding(horizontal = 16.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                // Tên sản phẩm
                Text(
                    text = product.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = FontFamily(Font(R.font.gelasio_bold))
                )

                // Giá và số lượng
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = product.price,
                        fontSize = 30.sp,
                        fontWeight = FontWeight(700),
                        fontFamily = FontFamily(Font(R.font.nunitosans_7pt_condensed_bold))
                    )

                    // Bộ chọn số lượng có thể là một `Composable` tùy chỉnh nếu cần
                    QuantitySelector()
                }

                // Đánh giá và mô tả
                Row(
                    modifier = Modifier.width(200.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "4.5",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(700),
                        fontFamily = FontFamily(
                            Font(R.font.nunitosans_7pt_condensed_bold)
                        ),
                        modifier = Modifier.padding(7.dp)
                    )
                    Text(
                        text = "(50 reviews)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = Color("#808080".toColorInt()),
                        fontFamily = FontFamily(
                            Font(R.font.nunitosans_7pt_condensed_bold)
                        ),
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .clickable {}
                    )
                }

                // Mô tả sản phẩm
                Text(
                    text = product.description,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight(500),
                    color = Color("#606060".toColorInt()),
                    fontFamily = FontFamily(Font(R.font.nunitosans_7pt_condensed_light))
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .size(60.dp)
                            .shadow(elevation = 2.dp, RoundedCornerShape(8.dp))
                            .background(color = Color("#F5F5F5".toColorInt())),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.marker),
                            contentDescription = null,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Row {
                        // Nút "Add to Cart" sử dụng `CustomButton`
                        CustomButton(
                            text = "Add to cart",
                            onClick = {
                                // Thêm logic "Add to cart" ở đây nếu cần
                            },
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth(),
                            backgroundColor = Color(0xFF242424),
                            textColor = Color.White
                        )
                    }
                }
            }
        } ?: run {
            // Hiển thị thông báo loading hoặc thông báo lỗi nếu productDetail là null
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading...") // Hoặc sử dụng CircularProgressIndicator
            }
        }
    }
}
    @Composable
    fun QuantitySelector() {
        Row(
            modifier = Modifier.width(113.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color = Color("#E0E0E0".toColorInt())),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = null,
                    modifier = Modifier.size(13.dp)
                )
            }
            Text(
                text = "01",
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                fontFamily = FontFamily(Font(R.font.nunitosans_7pt_condensed_bold))
            )
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color = Color("#E0E0E0".toColorInt())),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.apart),
                    contentDescription = null,
                    modifier = Modifier.size(13.dp)
                )
            }
        }
    }
