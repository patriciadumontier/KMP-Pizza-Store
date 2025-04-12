package org.example.project.api

import com.example.shared.models.PizzaOrder
import com.example.shared.models.OrderResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.request.setBody
import io.ktor.client.statement.*
import io.ktor.client.call.*
import org.example.project.models.OrderResponse
import org.example.project.models.PizzaOrder

/** Declare the expected function for creating an HTTP client. */
expect fun createHttpClient(): HttpClient

/**
 * RPC-style function that sends a PizzaOrder via HTTP POST to the server,
 * and returns the server’s response as an OrderResponse.
 */
suspend fun sendPizzaOrder(order: PizzaOrder): OrderResponse {
    val client = createHttpClient()
    val response: HttpResponse = client.post("http://localhost:8080/order") {
        setBody(order)
    }
    return response.body()
}