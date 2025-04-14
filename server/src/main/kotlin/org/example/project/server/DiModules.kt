package org.example.project.server

import org.example.project.shared.models.PizzaService
import org.koin.dsl.module

val pizzaModule = module {
    single { PizzaService() }
}