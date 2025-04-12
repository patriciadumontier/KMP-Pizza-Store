package org.example.project

import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.request.*
import org.example.project.models.OrderResponse
import org.example.project.models.PizzaOrder
import org.example.project.models.PizzaService
import org.koin.ktor.plugin.Koin
import org.koin.ktor.ext.get

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    install(Koin) {
        // Load our DI module
        modules(pizzaModule)
    }

    routing {
        post("/order") {
            // Receive a PizzaOrder from the client (in JSON)
            val order = call.receive<PizzaOrder>()

            // Get our PizzaService from Koin
            val pizzaService = get<PizzaService>()
            val response: OrderResponse = pizzaService.placeOrder(order)
            call.respond(response)
        }
    }
}
