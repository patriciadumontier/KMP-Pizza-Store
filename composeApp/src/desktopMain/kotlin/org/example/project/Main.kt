package org.example.project

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.project.models.OrderResponse
import org.example.project.models.PizzaOrder
import io.ktor.client.*

@Composable
@Preview
fun AppPreview() {
    App()
}

@Composable
fun App() {
    var customerName by remember { mutableStateOf("") }
    var pizzaType by remember { mutableStateOf("") }
    var quantityText by remember { mutableStateOf("1") }
    var address by remember { mutableStateOf("") }
    var responseMessage by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = pizzaType,
            onValueChange = { pizzaType = it },
            label = { Text("Pizza Type") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = quantityText,
            onValueChange = { quantityText = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Delivery Address") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                val quantity = quantityText.toIntOrNull() ?: 1
                val order = PizzaOrder(
                    customerName = customerName,
                    pizzaType = pizzaType,
                    quantity = quantity,
                    address = address
                )
                scope.launch {
                    val orderResponse: OrderResponse = sendPizzaOrder(order)
                    responseMessage = orderResponse.message
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Place Order")
        }
        Spacer(Modifier.height(16.dp))
        if (responseMessage.isNotEmpty()) {
            Text(text = responseMessage)
        }
    }
}

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

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pizza Order App",
    ) {
        App()
    }
}