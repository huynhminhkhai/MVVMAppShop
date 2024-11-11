package com.khai.dev.mvvmappshop.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.khai.dev.mvvmappshop.ui.screen.home.AccountScreen
import com.khai.dev.mvvmappshop.ui.screen.home.FavoriteScreen
import com.khai.dev.mvvmappshop.ui.screen.home.HomeScreen
import com.khai.dev.mvvmappshop.ui.screen.home.NotificationScreen


enum class Screen_local{
    Home,
    Favorite,
    Notification,
    Profile,
}
@Composable
fun LocalNavigation(
    mainNavController: NavController,
    localNavController: NavHostController,
    innerPadding: PaddingValues
){
    NavHost(
        navController = localNavController,
        startDestination = Screen_local.Home.name,
        modifier = Modifier.padding(innerPadding)) {
        composable(Screen_local.Home.name){ HomeScreen(innerPadding, mainNavController) }
        composable(Screen_local.Favorite.name){ FavoriteScreen(innerPadding, localNavController) }
        composable(Screen_local.Notification.name){ NotificationScreen(innerPadding,localNavController) }
        composable(Screen_local.Profile.name){ AccountScreen(innerPadding, mainNavController) }
    }
}