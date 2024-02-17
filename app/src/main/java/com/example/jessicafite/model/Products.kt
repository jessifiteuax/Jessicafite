package com.example.jessicafite.model

import androidx.compose.runtime.Composable
import com.example.jessicafite.R

data class Product(
    val title: String,
    val description: String,
    val price: Float,
    val img: Int
)
//lista de productos
@Composable
fun getProducts(): List<Product>{
    return listOf(
        Product("Escalera de 4 peldaños", "Escalera de espuma de 4 peldaños para sofá o cama 49x75x40", 70.50f, R.drawable.semicuatro),
        Product("Escalera de 4 peldaños plegable", "Escalera de lona de 4 peldaños plegable y transportable 80x92x46", 46.99f, R.drawable.cuatroplegable),
        Product("Escalera de 2+2 peldaños", "Escalera de madera con 2 peldaños ampliable a otros 2 más, desenfundable 44x60x40", 42.50f, R.drawable.dosmasdos),
        Product("Rampa para mascotas", "Rampa para sofá o cama, ajustable y antideslizante 70x34", 29.90f, R.drawable.rampa),
        Product("Escalera de 5 peldaños", "Escalera de espuma de 5 peldaños para sofá o cama 61x86x41", 89.90f, R.drawable.semicinco),
        Product("Escalera de 4 peldaños", "Escalera fija de 4 peldaños, no desenfundable 40x60x35", 49.90f, R.drawable.cuatro),
        Product("Escalera de 2 peldaños", "Escalera de espuma de 2 peldaños para sofá o cama 35x55x40", 44.50f, R.drawable.semidos),
        Product("Escalera de 3 peldaños", "Escalera de espuma de 3 peldaños para sofá o cama 40x57x40", 53.90f, R.drawable.semitres)
    )
}