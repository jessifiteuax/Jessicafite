package com.example.jessicafite.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jessicafite.model.Product
import com.example.jessicafite.viewModels.CartViewModel
import kotlinx.coroutines.launch

@Composable
fun CartCard(producto: Product, cartVM: CartViewModel) {
    //val scope = rememberCoroutineScope()
    //val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Column {
            Image(
                painter = painterResource(id = producto.img),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = producto.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = producto.description, color = Color.Gray)
            Spacer(modifier = Modifier.padding(5.dp))
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp), thickness = 2.dp, color = Color.Gray
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = producto.price.toString()+" €",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier.weight(0.7f))
                /* Text(text = "Añadir al carrito",
                     modifier = Modifier
                         .weight(0.3f)
                         .clickable { cartVM.agregarAlCarrito(producto) })*/
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    modifier = Modifier
                        .weight(0.3f)
                        .clickable {
                            cartVM.eliminarDelCarrito(producto)
                            cartVM.sumaCarrito -= producto.price
                            Toast.makeText(context, "Producto eliminado del carrito", Toast.LENGTH_SHORT).show()
                            /*scope.launch {
                                snackbarHostState.showSnackbar("Producto eliminado del carrito")
                            }*/
                        })
            }

        }
    }

}
