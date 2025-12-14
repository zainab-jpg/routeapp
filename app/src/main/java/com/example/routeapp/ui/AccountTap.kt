package com.example.routeapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.routeapp.data.DataStoreManager
import kotlinx.coroutines.flow.first

@Composable
fun AccountTab() {

    val context = LocalContext.current
    val dataStore = remember { DataStoreManager(context) }
    val scope = rememberCoroutineScope()

    var token by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        token = dataStore.getToken().first() ?: ""
    }

    Scaffold(
        bottomBar = { BottomBar() }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {


            Text(
                text = "Welcome, Mohamed",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF06004F)
            )

            Text(
                text = "mohamed.N@gmail.com",
                color = Color.Gray,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )

            Spacer(modifier = Modifier.height(24.dp))

            AccountField("Your full name", "Mohamed Mohamed Nabil")
            AccountField("Your E-mail", "mohamed.N@gmail.com")
            AccountField("Your password", "**************")
            AccountField("Your mobile number", "01122118855")
            AccountField("Your Address", "6th October, street 11...")
        }
    }
}

@Composable
fun AccountField(title: String, value: String) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = title,
            color = Color(0xFF06004F),
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "edit"
                )
            },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
@Composable
fun BottomBar() {

    NavigationBar(
        containerColor = Color(0xFF004182)
    ) {

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Home, null, tint = Color.White) },
            label = { Text("Home", color = Color.White) }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Favorite, null, tint = Color.White) },
            label = { Text("Wishlist", color = Color.White) }
        )

        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Person, null, tint = Color.White) },
            label = { Text("Account", color = Color.White) }
        )
    }
}
