package org.example.project.server.repository

import org.example.project.server.db.ManufacturerTable
import org.example.project.shared.models.Manufacturer
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ManufacturerRepository {
    fun fetchAllManufacturers(): List<Manufacturer> {
        return transaction {
            ManufacturerTable.selectAll().map { row ->
                Manufacturer(
                    id = row[ManufacturerTable.id],
                    name = row[ManufacturerTable.name]
                )
            }
        }
    }
}