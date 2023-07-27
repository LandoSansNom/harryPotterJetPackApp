package com.cherlanmiche.hpjetpackapp.ui.navigation.navgraph


import androidx.navigation.NavHostController
import com.cherlanmiche.hpjetpackapp.ui.navigation.navgraph.AllDestinations.CHARACTERS

object AllDestinations {
    const val CHARACTERS = "Characters"
    const val MEALOFDAY = "Meal Of Day"
    const val CATEGORIES = "Categories"
    const val SETTINGS = "Settings"
}

class AppNavigationActions(private val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(CHARACTERS) {
            popUpTo(CHARACTERS)
        }
    }
}