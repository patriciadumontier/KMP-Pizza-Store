package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun startComposeApp(): UIViewController {
    // This function can be called from Swift to obtain a view controller
    // that presents your shared Compose UI.
    return ComposeUIViewController {
        PizzaOrderApp()
    }
}