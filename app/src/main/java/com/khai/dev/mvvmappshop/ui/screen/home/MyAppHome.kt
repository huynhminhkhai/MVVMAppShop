package com.khai.dev.mvvmappshop.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.khai.dev.mvvmappshop.R
import com.khai.dev.mvvmappshop.ui.navigation.LocalNavigation
import com.khai.dev.mvvmappshop.ui.navigation.Screen_local
import com.khai.dev.mvvmappshop.ui.navigation.Screen_main


//MyAppHome
@Composable
fun MyAppHome(mainNavController: NavController) {
    val localNavController = rememberNavController()
    val itemsBottom = listOf(
        ItemBottomNavigation(
            Screen_local.Home.name,
            Icons.Default.Home,
            Icons.Outlined.Home
        ),
        ItemBottomNavigation(
            Screen_local.Favorite.name,
            Icons.Default.Favorite,
            Icons.Outlined.Favorite
        ),
        ItemBottomNavigation(
            Screen_local.Notification.name,
            Icons.Default.Notifications,
            Icons.Outlined.Notifications
        ),
        ItemBottomNavigation(
            Screen_local.Profile.name,
            Icons.Default.Person,
            Icons.Outlined.Person
        )
    )
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopNavigationBar(localNavController = localNavController, mainNavController = mainNavController)
            },
            bottomBar = {
                BottomNavigationBar(
                    items = itemsBottom,
                    selectedIndex = selectedIndex,
                    onItemSelected = {
                            index ->
                        selectedIndex = index
                        localNavController.navigate(itemsBottom[index].title)
                    }
                )
            }
        ) { innerPadding ->
            LocalNavigation(
                mainNavController = mainNavController,
                localNavController = localNavController,
                innerPadding = innerPadding
            )
        }
    }
}




//TopBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(localNavController: NavHostController, mainNavController: NavController) {
    val getNavRouterNow by localNavController.currentBackStackEntryAsState()
    val currentRouter = getNavRouterNow?.destination?.route ?: Screen_local.Home.name

    val homeTitle = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight(400),
                fontFamily = FontFamily(Font(R.font.gelasio_bold))
            )
        ) {
            append("Make home\n")
        }
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                fontFamily = FontFamily(Font(R.font.gelasio_bold))
            )
        ) {
            append("BEAUTIFUL")
        }
    }

    val title = when (currentRouter) {
        Screen_local.Home.name -> homeTitle
        Screen_local.Favorite.name -> "Favorite"
        Screen_local.Notification.name -> "Notification"
        Screen_local.Profile.name -> "Profile"
        else -> {
            "Chưa có"
        }
    }

    TopAppBar(
        title = {
            if (title is AnnotatedString) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            } else if (title is String) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = FontFamily(
                        Font(R.font.merriweather_regular)
                    )
                )
            }
        },
        actions = {
            IconButton(
                onClick = {mainNavController.navigate(Screen_main.cart.name)}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}

//BottomNavigationBar
data class ItemBottomNavigation(
    val title: String,
    val icon: ImageVector,
    val unIcon: ImageVector
)
@Composable
fun BottomNavigationBar(
    items: List<ItemBottomNavigation>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
){
    NavigationBar( containerColor = Color("#ffffff".toColorInt())) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                onClick = {onItemSelected(index)},
                selected = selectedIndex == index,
                icon = {
                    Icon(
                        imageVector = if (selectedIndex == index) item.icon else item.unIcon,
                        modifier = Modifier.size(24.dp),
                        contentDescription = item.title
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}