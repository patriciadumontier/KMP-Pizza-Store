package org.example.project.shared.models

import org.example.project.shared.api.PizzaRPC

/**
 * The PizzaService class implements the PizzaRPC interface.
 * Here, it processes a pizza order and returns a confirmation message.
 * In a real-world scenario, you might save the order to a database or
 * communicate with other systems (e.g., payment gateways, email services).
 */
class PizzaService : PizzaRPC {
    override suspend fun placeOrder(order: PizzaOrder): OrderResponse {
        // You can insert business logic here (database operations, external service calls, etc.)
        println("Received order from ${order.customerName} for ${order.quantity} ${order.pizzaType}(s) at ${order.address}.")

        // Create a response message to confirm the order is received
        val message = "Order received! Preparing ${order.quantity} ${order.pizzaType}(s) for delivery to ${order.address}."
        return OrderResponse(message)
    }
}