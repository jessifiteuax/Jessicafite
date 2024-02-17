package com.example.jessicafite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.jessicafite.navigation.NavManager
import com.example.jessicafite.ui.theme.JessicafiteTheme
import com.example.jessicafite.viewModels.CartViewModel
import com.example.jessicafite.viewModels.LoginViewModel
import com.example.jessicafite.viewModels.ProductsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginVM : LoginViewModel by viewModels()
        val productsVM : ProductsViewModel by viewModels()
        val cartVM: CartViewModel by viewModels()

        setContent {
            JessicafiteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(loginVM = loginVM, productsVM, cartVM)
                }
            }
        }
    }
}

