package org.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.project.api.sendPizzaOrder
import org.example.project.models.PizzaOrder

@Composable
fun PizzaOrderApp() {
    var customerName by remember { mutableStateOf("") }
    var pizzaType by remember { mutableStateOf("") }
    var quantityText by remember { mutableStateOf("1") }
    var address by remember { mutableStateOf("") }
    var responseMessage by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Pizza Order", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = pizzaType,
            onValueChange = { pizzaType = it },
            label = { Text("Pizza Type") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = quantityText,
            onValueChange = { quantityText = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Delivery Address") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val quantity = quantityText.toIntOrNull() ?: 1
                val order = PizzaOrder(customerName, pizzaType, quantity, address)
                scope.launch {
                    responseMessage = try {
                        sendPizzaOrder(order).message
                    } catch (e: Exception) {
                        "Error: ${e.message}"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Place Order")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (responseMessage.isNotEmpty()) {
            Text(responseMessage)
        }
    }
}