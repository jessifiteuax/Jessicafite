package com.example.jessicafite.viewModels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.jessicafite.model.Product

class CartViewModel : ViewModel() {
    var shoppingCart by mutableStateOf<List<Product>>(emptyList())
        private set

    fun agregarAlCarrito(producto: Product) {
        shoppingCart += producto
    }

    fun eliminarDelCarrito(producto: Product) {
        shoppingCart = shoppingCart - producto
    }

    var sumaCarrito: Float = 0f
}
