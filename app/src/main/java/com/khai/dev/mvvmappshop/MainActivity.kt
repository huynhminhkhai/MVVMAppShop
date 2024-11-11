package com.khai.dev.mvvmappshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.khai.dev.mvvmappshop.ui.navigation.MainNavigation
import com.khai.dev.mvvmappshop.ui.theme.MVVMAppShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMAppShopTheme {
                Main()
            }
        }
    }
}
@Composable
fun Main() {
    val mainNavController = rememberNavController()
    MainNavigation(mainNavController)
}
