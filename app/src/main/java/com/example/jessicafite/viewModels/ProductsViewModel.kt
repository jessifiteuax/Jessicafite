package com.example.jessicafite.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jessicafite.model.ProductsState
import com.example.jessicafite.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class ProductsViewModel:ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore

    private val _productsData = MutableStateFlow<List<ProductsState>>(emptyList())
    val productsData: StateFlow<List<ProductsState>> = _productsData



    /*fun fetchProducts() {
        firestore.collection("Productos")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val documents = mutableListOf<ProductsState>()
                for (document in querySnapshot.documents) {
                    val myDocument = document.toObject(ProductsState::class.java)?.copy(idDoc = document.id)
                    myDocument?.let { documents.add(it) }
                }
                _productsData.value = documents
            }
            .addOnFailureListener { exception ->
                Log.e("Error", "Error al hacer fetching products", exception)
            }
    }*/


    fun fetchProducts(){
        firestore.collection("Productos")
            .addSnapshotListener {querySnapshot, error ->
                if (error != null){
                    return@addSnapshotListener
                }
                val documents = mutableListOf<ProductsState>()
                if (querySnapshot != null){
                    for (document in querySnapshot){
                        val myDocument = document.toObject(ProductsState::class.java)
                        documents.add(myDocument)
                    }
                }
                _productsData.value = documents
            }
    }

    fun signOut(){
        auth.signOut()
    }
}