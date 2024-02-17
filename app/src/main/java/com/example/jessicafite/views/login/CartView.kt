package com.example.jessicafite.views.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jessicafite.components.CartCard
import com.example.jessicafite.components.ProductCard
import com.example.jessicafite.model.getProducts
import com.example.jessicafite.viewModels.CartViewModel
import com.example.jessicafite.viewModels.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartView(
    navController: NavHostController,
    cartVM: CartViewModel
) {

    /*LaunchedEffect(Unit) {
        productsVM.fetchProducts()
    }*/

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Carrito") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
            )
        },
        content = {padding ->
            ContentCart(padding, cartVM)
        }
    )
}

@Composable
fun ContentCart(padding: PaddingValues, cartVM: CartViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        val cartList = cartVM.shoppingCart
        LazyColumn {
            items(cartList) { producto ->
                CartCard(producto, cartVM)
            }
        }
        Row {
            val formattedPrice = String.format("%.2f", cartVM.sumaCarrito)
            Text(text = "Total: ${formattedPrice}€",
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp)
        }
    }
}

