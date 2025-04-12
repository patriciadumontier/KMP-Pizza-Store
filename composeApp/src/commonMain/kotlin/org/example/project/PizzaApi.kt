package org.example.project

import org.example.project.models.OrderResponse
import org.example.project.models.PizzaOrder
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

// Declare an expected function to create an HttpClient.
expect fun createHttpClient(): HttpClient

// This function uses the client to send the PizzaOrder and returns the response.
suspend fun sendPizzaOrder(order: PizzaOrder): OrderResponse {
    val client = createHttpClient()
    return client.post("http://localhost:8080/order") {
        setBody(order)
    }.body()
}