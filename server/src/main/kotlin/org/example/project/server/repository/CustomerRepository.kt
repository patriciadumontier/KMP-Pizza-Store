package org.example.project.server.repository

import org.example.project.shared.models.Customer


class CustomerRepository {
    fun fetchAllCustomers(): List<Customer> {
        return transaction {
            CustomerTable.selectAll().map { row ->
                Customer(
                    id = row[CustomerTable.id],
                    customerName = row[CustomerTable.customerName],
                    address = row[CustomerTable.address],
                    phone = row[CustomerTable.phone]
                )
            }
        }
    }
}