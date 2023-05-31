package com.example.navhostex

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.navhostex.ui.theme.NavHostExTheme
import java.lang.Exception

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavHostExTheme {
                MainScreenView()
            }
        }
    }
}

enum class MainRoutes(val route: String){
    Home("home"),
    Profile("profile")
}

enum class SharedRoutes(val route: String){
    Settings("settings")
}


fun NavGraphBuilder.sharedSettingsGraph(navController: NavController){
    composable(SharedRoutes.Settings.route) {
        SettingsScreen(
            onNavigate = { navController.navigate(it.route) },
            onNavigateBackToHome = {
                navController.popBackStack(MainRoutes.Home.route, inclusive = false)
                navController.navigate(MainRoutes.Home.route)
            }
        )
    }
}

@Composable
fun HomeScreen(onNavigate: (SharedRoutes) -> Unit){
    Column{
        Text(text = "Hello home")
        Button(onClick = { onNavigate(SharedRoutes.Settings) }) {
            Text(text = "Setting으로 이동")
        }
    }
}

@Composable
fun ProfileScreen(onNavigate: (SharedRoutes) -> Unit){
    Column{
        Text(text = "Profile")
        Button(onClick = { onNavigate(SharedRoutes.Settings) }) {
            Text(text = "Setting으로 이동")
        }
    }
}

@Composable
fun SettingsScreen(onNavigate: (MainRoutes) -> Unit, onNavigateBackToHome: () -> Unit){
    Column{
        Text(text = "Setting")
        Button(onClick = { onNavigate(MainRoutes.Home) }) {
            Text(text = "Home으로 이동")
        }
        Button(onClick = { onNavigate(MainRoutes.Profile) }) {
            Text(text = "Profile으로 이동")
        }
        Button(onClick =  onNavigateBackToHome) {
            Text(text = "뒤로 이동")
        }
    }
}

@Composable
fun MainNavigation(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = MainRoutes.Home.route){
        composable(MainRoutes.Home.route){
            HomeScreen(onNavigate = {navController.navigate(it.route)})
        }
        composable(MainRoutes.Profile.route){
            ProfileScreen(onNavigate = {navController.navigate(it.route)})
        }
        sharedSettingsGraph(navController)
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        Box(Modifier.padding(it)) {
            MainNavigation()
        }
    }
}
@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf(
        BottomScreen.Main,
        BottomScreen.Community,
        BottomScreen.My,
        BottomScreen.Setting
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = if (currentRoute?.startsWith(item.screenRoute) == true) painterResource(
                            id = item.iconSolid
                        ) else painterResource( id = item.iconOutline),
                        contentDescription = item.title,
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                },
                label = { Text(item.title, fontSize = 11.sp) },
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = MaterialTheme.colors.secondary,
                selected = currentRoute?.startsWith(item.screenRoute) == true,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        if (currentRoute?.startsWith(item.screenRoute) != true) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

sealed class BottomScreen(
    val title: String,
    @DrawableRes val iconOutline: Int,
    @DrawableRes val iconSolid: Int,
    val screenRoute: String
) {
    object Main : BottomScreen("메인", R.drawable.home_outline, R.drawable.home_outline, "main")

    object Community :
        BottomScreen("커뮤니티",  R.drawable.home_outline, R.drawable.home_outline,"community")

    object My : BottomScreen("마이페이지",  R.drawable.home_outline, R.drawable.home_outline, "my")

    object Setting :
        BottomScreen("설정",  R.drawable.home_outline, R.drawable.home_outline, "setting")
}
