package com.khai.dev.mvvmappshop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khai.dev.mvvmappshop.ui.screen.auth.LoginScreen
import com.khai.dev.mvvmappshop.ui.screen.auth.RegisterScreen
import com.khai.dev.mvvmappshop.ui.screen.auth.WelcomeScreen

enum class Screen_main {
    home,
    welcome,
    login,
    register,
    detail,
    cart
}

@Composable
fun MainNavigation(mainNavController: NavHostController) {
    NavHost(navController = mainNavController, startDestination = Screen_main.welcome.name) {
        composable(Screen_main.welcome.name) { WelcomeScreen(mainNavController) }
        composable(Screen_main.login.name) { LoginScreen(mainNavController) }
        composable(Screen_main.register.name) { RegisterScreen(mainNavController) }
    }
}
