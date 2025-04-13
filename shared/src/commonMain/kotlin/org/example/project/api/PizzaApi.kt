package org.example.project.api

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.request.setBody
import io.ktor.client.call.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
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
    return client.post("http://localhost:8080/order") {
        contentType(ContentType.Application.Json)
        setBody(order)
    }.body()
}