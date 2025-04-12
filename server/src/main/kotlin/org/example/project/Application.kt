package org.example.project

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.request.*
import kotlinx.serialization.json.Json
import org.example.project.models.OrderResponse
import org.example.project.models.PizzaOrder
import org.example.project.models.PizzaService
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.plugin.Koin
import org.koin.ktor.ext.get

// Define the Exposed table for pizza orders.
object Orders : Table("orders") {
    val id = integer("id").autoIncrement()
    val customerName = varchar("customer_name", length = 100)
    val pizzaType = varchar("pizza_type", length = 50)
    val quantity = integer("quantity")
    val address = varchar("address", length = 200)
    override val primaryKey = PrimaryKey(id, name = "PK_Orders_ID")
}

// Set up a Database Factory to initialize PostgreSQL with HikariCP.
object DatabaseFactory {
    fun init() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://localhost:5432/pizza_db"  // Change to your database URL.
            driverClassName = "org.postgresql.Driver"
            username = "yourusername"   // Set your PostgreSQL username.
            password = "yourpassword"   // Set your PostgreSQL password.
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        }
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)
        transaction {
            SchemaUtils.create(Orders)
        }
    }
}

// PizzaService: processes orders and stores them in the database.
class PizzaService {
    suspend fun placeOrder(order: PizzaOrder): OrderResponse {
        transaction {
            Orders.insert { row ->
                row[customerName] = order.customerName
                row[pizzaType] = order.pizzaType
                row[quantity] = order.quantity
                row[address] = order.address
            }
        }
        return OrderResponse("Order received! Preparing ${order.quantity} ${order.pizzaType}(s) for delivery to ${order.address}.")
    }
}

fun main() {
    // Initialize the PostgreSQL database.
    DatabaseFactory.init()

    // Start Ktor server on port 8080.
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Koin) {
            modules(pizzaModule)
        }
        routing {
            post("/order") {
                // Receive the pizza order from the client.
                val order = call.receive<PizzaOrder>()
                // Retrieve the PizzaService from Koin.
                val pizzaService = get<PizzaService>()
                // Process the order.
                val response: OrderResponse = pizzaService.placeOrder(order)
                call.respond(response)
            }
        }
    }.start(wait = true)
}
