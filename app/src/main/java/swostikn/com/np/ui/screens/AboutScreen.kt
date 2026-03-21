package swostikn.com.np.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AboutScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "About PDF Summarizer", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        SectionTitle("What is this app?")
        Text(
            "PDF Summarizer is an AI-powered tool designed specifically for students to interact with their college slides and study materials. It helps you quickly extract key information from long documents.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionTitle("How it works")
        Text(
            "1. Upload your college slides (PDF format).\n" +
            "2. Our AI processes the text and understands the context.\n" +
            "3. Ask any questions about the material in the chat interface.\n" +
            "4. Get instant answers based on the content of your PDF.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionTitle("Why use it?")
        Text(
            "Studying for exams can be overwhelming. This app allows students to quickly find definitions, summaries, and explanations from their own lecture notes without manually searching through hundreds of slides.",
            style = MaterialTheme.typography.bodyMedium
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}
