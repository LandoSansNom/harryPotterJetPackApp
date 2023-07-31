package com.cherlanmiche.hpjetpackapp.ui.navigation.navgraph


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cherlanmiche.hpjetpackapp.ui.navigation.sidedrawer.AppDrawer
import com.cherlanmiche.hpjetpackapp.ui.screens.CharacterDetailsScreen
import com.cherlanmiche.hpjetpackapp.ui.screens.CharactersScreen
import com.cherlanmiche.hpjetpackapp.ui.screens.CharactersViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun hpJetPackAppNavGraph(
    viewModel: CharactersViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: AllDestinations.CHARACTERS
    val navigationActions = remember(navController) {
        AppNavigationActions(navController)
    }

    ModalNavigationDrawer(drawerContent = {
        AppDrawer(
            route = currentRoute,
            modifier= Modifier,
            navigateToHome = { navigationActions.navigateToHome() }
        ) { coroutineScope.launch { drawerState.close() } }
    }, drawerState = drawerState) {
        Scaffold(

            topBar = {
                if (currentRoute != "CharacterDetailsScreen/{characterId}") {
                    TopAppBar(
                        title = {

                            Text(text = currentRoute)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch { drawerState.open() }
                            }, content = {
                                Icon(
                                    imageVector = Icons.Default.Menu, contentDescription = null
                                )
                            })
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    )
                }else{
                    TopAppBar(
                        title = {

                            Text(text = "Details")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.navigateUp()
                            }, content = {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
                                )
                            })
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    )
                } }, modifier = Modifier
        ) { it ->
            NavHost(
                navController = navController,
                startDestination = AllDestinations.CHARACTERS,
                modifier = modifier.padding(it)
            ) {

                composable("CharacterDetailsScreen/{characterId}") { backStackEntry ->
                    val characterId = backStackEntry.arguments?.getString("characterId")
                    val character = viewModel.characters.value?.firstOrNull { it.id == characterId }
                    if (character != null) {
                        CharacterDetailsScreen(character)
                    }
                }

                composable(AllDestinations.CHARACTERS) {
                    CharactersScreen(navController, viewModel)
                }

            }
        }
    }
}