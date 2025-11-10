package com.example.miniprofilehost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.miniprofilehost.ui.theme.MiniProfileHostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = intent.getStringExtra("USER_ID")
        val userName = intent.getStringExtra("USER_NAME")

        setContent {
            MiniProfileHostTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    if (userId != null && userName != null) {
                        ProfileScreen(userId = userId, userName = userName)
                    } else {
                        ProfileScreen(userId = "default", userName = "Guest")
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(userId: String, userName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mini Profile",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = "User ID: $userId",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "User Name: $userName",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MiniProfileHostTheme {
        ProfileScreen(userId = "12345", userName = "Sample User")
    }
}