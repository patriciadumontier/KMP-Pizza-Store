package org.example.project.api

import org.example.project.models.OrderResponse
import org.example.project.models.PizzaOrder

interface PizzaRPC {
    suspend fun placeOrder(order: PizzaOrder): OrderResponse
}