package com.cherlanmiche.hpjetpackapp.ui.navigation.navgraph


import androidx.navigation.NavHostController
import com.cherlanmiche.hpjetpackapp.ui.navigation.navgraph.AllDestinations.CHARACTERS

object AllDestinations {
    const val CHARACTERS = "Characters"

}

class AppNavigationActions(private val navController: NavHostController) {

    fun navigateToHome() {
        navController.navigate(CHARACTERS) {
            popUpTo(CHARACTERS)
        }
    }
}