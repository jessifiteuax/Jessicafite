package com.example.jessicafite.views.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.jessicafite.components.ProductCard
import com.example.jessicafite.model.getProducts
import com.example.jessicafite.viewModels.ProductsViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.jessicafite.viewModels.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavHostController,
    productsVM: ProductsViewModel,
    cartVM: CartViewModel
) {
    /*LaunchedEffect(Unit) {
        productsVM.fetchProducts()
    }*/

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Productos") },
                navigationIcon = {
                    IconButton(onClick = {
                        productsVM.signOut()
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("Cart")
                    }) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
                    }
                }
            )
        },
        content = {padding ->
            ContentHome(padding, cartVM)
        }
    )
}

@Composable
fun ContentHome(padding: PaddingValues, cartVM: CartViewModel) {


    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        val productosList = getProducts()
        LazyColumn {
            items(productosList) { producto ->
                ProductCard(producto, cartVM)
            }
        }
    }
}


