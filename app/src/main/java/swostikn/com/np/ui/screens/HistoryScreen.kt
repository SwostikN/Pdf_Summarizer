package swostikn.com.np.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class ChatHistory(val id: String, val title: String, val date: String)

@Composable
fun HistoryScreen(navController: NavController) {
    val historyItems = listOf(
        ChatHistory("1", "Physics Lecture 1", "2023-10-25"),
        ChatHistory("2", "OS Chapter 3", "2023-10-24"),
        ChatHistory("3", "Data Structures Intro", "2023-10-22")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Chat History", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(historyItems) { item ->
                HistoryItem(item) {
                    // Logic to view full conversation
                }
            }
        }
    }
}

@Composable
fun HistoryItem(item: ChatHistory, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.History, contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = item.title, style = MaterialTheme.typography.titleMedium)
                Text(text = item.date, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
