package com.khai.dev.mvvmappshop.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit
import com.khai.dev.mvvmappshop.R

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF242424),
    textColor: Color = Color.White,
    width: Dp = 285.dp,   // Chiều rộng tùy chỉnh
    height: Dp = 48.dp,           // Chiều cao tùy chỉnh
    fontSize: TextUnit = 18.sp    // Kích cỡ chữ tùy chỉnh
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
//            .shadow(elevation = 5.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable {onClick()}
    ){
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = text,
                color = textColor,
                fontFamily = FontFamily(Font(R.font.gelasio_bold)),
                fontWeight = FontWeight(300),
                fontSize = fontSize
            )
        }
    }


}
