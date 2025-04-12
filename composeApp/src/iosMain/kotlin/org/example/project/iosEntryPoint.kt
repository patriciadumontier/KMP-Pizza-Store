package org.example.project

import androidx.compose.ui.window.Application
// This is a placeholder—Compose for iOS integration is experimental.
// Typically, you will package your shared UI (PizzaOrderApp) into a framework,
// then call it from SwiftUI or UIKit in Xcode.

fun startComposeApp() {
    // Call your shared composable—this function can be invoked by Swift.
    Application("Pizza Order App") {
        PizzaOrderApp()
    }
}