package com.example.jessicafite.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jessicafite.viewModels.CartViewModel
import com.example.jessicafite.viewModels.LoginViewModel
import com.example.jessicafite.viewModels.ProductsViewModel
import com.example.jessicafite.views.login.CartView
import com.example.jessicafite.views.login.TabsView
import com.example.jessicafite.views.products.HomeView

//navegacion views
@Composable
fun NavManager(
    loginVM: LoginViewModel, productsVM: ProductsViewModel, cartVM: CartViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login" ){

        composable("Login"){
            TabsView(navController, loginVM)
        }
        composable("Home"){
            HomeView(navController, productsVM, cartVM)
        }
       composable("Cart"){
            CartView(navController, cartVM)
        }
    }
}