package com.example.jessicafite.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.jessicafite.model.Product

@Composable
fun ProductDialog(producto: Product, show: Boolean, onDismiss: () -> Unit) {

    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() },
        ) {
            Column (
                modifier = Modifier
                    .background(Color.White)
                    .padding(10.dp)
                    .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = producto.img),
                    contentDescription = null,
                    modifier = Modifier
                        .size(220.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = producto.title, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = producto.description, color = Color.Gray)
            }
        }
    }


}

