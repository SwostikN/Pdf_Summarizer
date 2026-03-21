package swostikn.com.np.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Message(val text: String, val isUser: Boolean)

@Composable
fun HomeScreen(navController: NavController) {
    var messages by remember { mutableStateOf(listOf<Message>()) }
    var inputText by remember { mutableStateOf("") }
    var isUploading by remember { mutableStateOf(false) }
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var fileName by remember { mutableStateOf<String?>(null) }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedFileUri = uri
        if (uri != null) {
            // In a real app, you would extract the file name from the URI
            fileName = "Selected PDF Document"
            isUploading = true
            // Simulate processing
            // In a real app, you'd trigger text extraction here
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // PDF Upload Section
        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.UploadFile, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = fileName ?: "Upload College Slides (PDF)",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = if (fileName != null) "File ready for chat" else "Extract text and chat with your slides",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Button(onClick = { filePickerLauncher.launch("application/pdf") }) {
                    Text(if (fileName != null) "Change" else "Upload")
                }
            }
        }

        if (isUploading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp))
            LaunchedEffect(isUploading) {
                kotlinx.coroutines.delay(2000) // Simulate processing time
                isUploading = false
                messages = messages + Message("I've processed your PDF! How can I help you with it today?", false)
            }
        }

        // Chat Interface
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                ChatBubble(message)
            }
        }

        // Input Area
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Ask something about the PDF...") }
            )
            IconButton(onClick = {
                if (inputText.isNotBlank()) {
                    messages = messages + Message(inputText, true)
                    // Simulate AI response
                    messages = messages + Message("Based on the PDF you uploaded, here is the information you requested...", false)
                    inputText = ""
                }
            }) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send")
            }
        }
    }
}

@Composable
fun ChatBubble(message: Message) {
    val alignment = if (message.isUser) Alignment.End else Alignment.Start
    val color = if (message.isUser) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalAlignment = alignment) {
        Surface(
            color = color,
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
