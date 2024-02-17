package com.example.jessicafite.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jessicafite.model.UserModel
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.Exception

class LoginViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    var showAlert by mutableStateOf(false)

    fun login(email: String, password: String, onSuccess: () -> Unit){
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            onSuccess()
                        }else{
                            Log.d("Error de login","Usuario o contrasena incorrectos")
                            showAlert = true
                        }
                    }
            }catch (e: Exception){
                Log.d("Error en la aplicacion", "Error: ${e.localizedMessage}")
            }
        }
    }

    fun loginGoogle(credential: AuthCredential, home:() -> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithCredential(credential)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Log.d("App", "LogIn exitoso")
                        home()
                    }
                }
                .addOnFailureListener {
                    Log.d("App", "LogIn fallido")
                }
        }catch (e:Exception){
            Log.d("App", "Error al hacer Login con Google " + "${e.localizedMessage}")
        }
    }

    fun createUser(email: String, password: String, username: String, onSuccess: () -> Unit){
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            saveUser(username)
                            onSuccess()
                        }else{
                            Log.d("Error de creacion usuario","Error al crear usuario")
                            showAlert = true
                        }
                    }
            }catch (e: Exception){
                Log.d("Error de la aplicacion", "ERROR: ${e.localizedMessage}")
            }
        }
    }

    private fun saveUser(username: String){
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        viewModelScope.launch(Dispatchers.IO){
            val user = UserModel(
                userId = id.toString(),
                email = email.toString(),
                username = username
            )

            FirebaseFirestore.getInstance().collection("Users")
                .add(user)
                .addOnSuccessListener {
                    Log.d("Ha guardado", "Ha guardado correctamente")
                }.addOnFailureListener{
                    Log.d("Error al guardar", "ERROR al guardar en firestore")
                }
        }
    }

    fun closeAlert(){
        showAlert = false
    }
}