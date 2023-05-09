package com.example.nestnav

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

@Composable
fun MyNestedNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = StartupNavigationScreens.Login.route) {
        composable(StartupNavigationScreens.Login.route) { Login(navController) }
        composable(StartupNavigationScreens.Register.route) { Register(navController) }
        mainGraph(navController) //notice no composable but will go to the mainGraph
    }
}

// Authentication screens
sealed class StartupNavigationScreens(val route: String) {
    object Login : StartupNavigationScreens("login")
    object Register : StartupNavigationScreens("register")
    object Main : StartupNavigationScreens("main")
}

// Main app screens
sealed class MainNavigationScreens(val route: String) {
    object Home : MainNavigationScreens("home")
    object Secondary : MainNavigationScreens("second")
}


fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(startDestination = MainNavigationScreens.Home.route, route = StartupNavigationScreens.Main.route) {
        composable(MainNavigationScreens.Home.route) { Home(navController) }
        composable(MainNavigationScreens.Secondary.route) { Secondary(navController) }
    }
}

@Composable
fun Home(navController: NavController) {
    // This composable represents the top-level screen
    // Add UI elements here to navigate to the nested screens
    Button(onClick = { navController.popBackStack() }) {
        Text("Go to nested screen 1")
    }
}

@Composable
fun Secondary(navController: NavController) {
    // This composable represents the first nested screen
    // Add UI elements here to navigate to the second nested screen
    Button(onClick = { navController.navigate("nested_navigation_2/second_nested_screen_1") }) {
        Text("Go to second nested screen 1")
    }
}

@Composable
fun Login(navController: NavController) {
    // This composable represents the second nested screen 1
    // Add UI elements here to navigate within this nested screen
    Button(onClick = { navController.navigate(MainNavigationScreens.Home.route) }) {
        //navController.navigate(MainNavigationScreens.Home.route)
        Text("Go back to previous screen")
    }
}

@Composable
fun Register(navController: NavController) {
    // This composable represents the second nested screen 2
    // Add UI elements here to navigate within this nested screen
    Button(onClick = { navController.popBackStack() }) {
        Text("Go back to previous screen")
    }
}