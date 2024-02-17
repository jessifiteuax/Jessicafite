package com.example.jessicafite.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.jessicafite.R
import com.example.jessicafite.components.Alert
import com.example.jessicafite.viewModels.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.launch

@Composable
fun RegisterView(navController: NavController, loginVM: LoginViewModel) {

    Scaffold(
        content = { padding ->
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (image, content) = createRefs()

                Image(
                    painter = painterResource(id = R.drawable.loginimg),
                    contentDescription = "",
                    modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .size(350.dp)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.constrainAs(content) {
                        top.linkTo(image.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                ) {
                    var username by remember { mutableStateOf("") }
                    var email by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    Text(
                        text = "Iniciar Sesión",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 20.dp)
                    )
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text(text = "Elige tu nombre de usuario") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        maxLines = 1
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Email") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 8.dp),
                        maxLines = 1
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "Contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 8.dp),
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            loginVM.createUser(email, password, username) {
                                navController.navigate("Home")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                    ) {
                        Text(
                            text = "Registrarse",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    if (loginVM.showAlert) {
                        Alert(title = "Error",
                            message = "Usuario no creado",
                            confirmText = "Aceptar",
                            onConfirmClick = { loginVM.closeAlert() }) {
                        }
                    }
                }
            }
        }
    )
}


/*ConstraintLayout(
    modifier = Modifier.fillMaxSize()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    val (image, usernameTextField, emailTextField, passwordTextField, buttonCreate) = createRefs()

    Image(
        painter = painterResource(id = R.drawable.loginimg),
        contentDescription = null,
        modifier = Modifier
            .constrainAs(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .size(350.dp)
    )

    OutlinedTextField(
        value = "",
        onValueChange = { username = it },
        label = { Text(text = "Elige tu nombre de usuario") },
        modifier = Modifier
            .constrainAs(usernameTextField) {
                top.linkTo(image.bottom, margin = 20.dp)
                start.linkTo(parent.start, margin = 30.dp)
                end.linkTo(parent.end, margin = 30.dp)
            }
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        maxLines = 1
    )

    OutlinedTextField(
        value = "",
        onValueChange = { email = it },
        label = { Text(text = "Email") },
        modifier = Modifier
            .constrainAs(emailTextField) {
                top.linkTo(usernameTextField.bottom, margin = 20.dp)
                start.linkTo(parent.start, margin = 30.dp)
                end.linkTo(parent.end, margin = 30.dp)
            }
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )

    OutlinedTextField(
        value = "",
        onValueChange = { password = it },
        label = { Text(text = "Contraseña") },
        modifier = Modifier
            .constrainAs(passwordTextField) {
                top.linkTo(emailTextField.bottom, margin = 20.dp)
                start.linkTo(parent.start, margin = 30.dp)
                end.linkTo(parent.end, margin = 30.dp)
            }
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        maxLines = 1,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )

    Button(
        onClick = {
            loginVM.createUser(email, password, username) {
                navController.navigate("Home")
            }
        },
        modifier = Modifier
            .constrainAs(buttonCreate) {
                top.linkTo(passwordTextField.bottom, margin = 20.dp)
                start.linkTo(parent.start, margin = 30.dp)
                end.linkTo(parent.end, margin = 30.dp)
            }
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Text(
            text = "Registrarse",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }

    if (loginVM.showAlert) {
        Alert(title = "Error",
            message = "Usuario no creado",
            confirmText = "Aceptar",
            onConfirmClick = { loginVM.closeAlert() }) {
        }
    }
}*/
