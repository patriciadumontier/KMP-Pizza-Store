package org.example.project.server

import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.request.*
import kotlinx.serialization.json.Json
import org.example.project.server.db.DatabaseFactory

fun main() {
    // Initialize the database connection.
    DatabaseFactory.init()

    // Start the Ktor server on port 8080.
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        routing {
            get("/manufacturers") {
                val repo = ManufacturerRepository()
                val manufacturers = repo.fetchAllManufacturers()
                call.respond(manufacturers)
            }
        }
    }.start(wait = true)
}