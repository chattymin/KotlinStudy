package com.example.navhostex

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.navhostex.ui.theme.NavHostExTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavHostExTheme {
                MainNavigation()
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