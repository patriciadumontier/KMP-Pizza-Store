package org.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.project.models.OrderResponse
import org.example.project.models.PizzaOrder
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PizzaOrderApp() {
    var customerName by remember { mutableStateOf("") }
    var pizzaType by remember { mutableStateOf("") }
    var quantityText by remember { mutableStateOf("1") }
    var address by remember { mutableStateOf("") }
    var responseMessage by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer Name") },
            modifier = Modifier.fillMaxWidth()
        )
        // ... additional UI elements similar to previous examples ...
        Button(onClick = {
            val quantity = quantityText.toIntOrNull() ?: 1
            val order = PizzaOrder(customerName, pizzaType, quantity, address)
            scope.launch {
                try {
                    val orderResponse: OrderResponse = sendPizzaOrder(order)
                    responseMessage = orderResponse.message
                } catch (e: Exception) {
                    responseMessage = "Error: ${e.message}"
                }
            }
        }) {
            Text("Place Order")
        }
        if (responseMessage.isNotEmpty()) {
            Text(responseMessage)
        }
    }
}

@Composable
@Preview
fun PizzaOrderAppPreview() {
    MaterialTheme {
        PizzaOrderApp()
    }
}

/*
suspend fun sendPizzaOrder(order: PizzaOrder): OrderResponse {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }
    // Adjust the URL if your server is hosted elsewhere.
    return client.post("http://localhost:8080/order") {
        setBody(order)
    }.body()
}
 */