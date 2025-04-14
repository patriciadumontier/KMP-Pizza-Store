package org.example.project.server.db

import org.jetbrains.exposed.sql.Table

object ManufacturerTable : Table("manufacturer") {
    val id = integer("manufacturer_id").autoIncrement() // Primary key
    val name = varchar("name", 255).uniqueIndex()
    override val primaryKey = PrimaryKey(id, name = "PK_Manufacturer_ID")
}