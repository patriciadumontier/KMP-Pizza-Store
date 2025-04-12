package org.example.project

import org.example.project.models.PizzaService
import org.koin.dsl.module

val pizzaModule = module {
    single { PizzaService() }
}