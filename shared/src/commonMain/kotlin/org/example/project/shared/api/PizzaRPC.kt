package org.example.project.shared.api

import org.example.project.shared.models.OrderResponse
import org.example.project.shared.models.PizzaOrder

interface PizzaRPC {
    suspend fun placeOrder(order: PizzaOrder): OrderResponse
}