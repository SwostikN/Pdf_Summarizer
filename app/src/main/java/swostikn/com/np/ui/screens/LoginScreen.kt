package swostikn.com.np.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import swostikn.com.np.ui.Screen
import swostikn.com.np.ui.viewmodel.LoginViewModel

/**
 * LoginScreen (View Layer)
 * This screen observes the ViewModel's LiveData and reacts to state changes.
 * It does not contain any business logic.
 */
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel() // Injecting ViewModel
) {
    // UI State for inputs
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
    // Observing LiveData from ViewModel
    val loginResult by viewModel.loginResult.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)

    // Handling the login result (Navigation or Error message)
    LaunchedEffect(loginResult) {
        loginResult?.let { result ->
            if (result.isSuccess) {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
                viewModel.clearResult()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email / Username") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )

        // Error Message Handling
        if (loginResult?.isFailure == true) {
            Text(
                text = loginResult?.exceptionOrNull()?.message ?: "Login failed",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button with Loading State
        Button(
            onClick = { viewModel.login(email, password) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading && email.isNotEmpty() && password.isNotEmpty()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text("Login")
            }
        }
    }
}
