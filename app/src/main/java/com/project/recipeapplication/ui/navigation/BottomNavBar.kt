package com.project.recipeapplication.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.recipeapplication.ui.components.AddRecipe
import com.project.recipeapplication.ui.components.ApiRecipeInfo
import com.project.recipeapplication.ui.components.PersonalRecipeInfo
import com.project.recipeapplication.ui.screens.Dashboard
import com.project.recipeapplication.ui.screens.Recipes
import com.project.recipeapplication.ui.screens.Search
import com.project.recipeapplication.ui.screens.ShoppingList
import com.project.recipeapplication.viewModel.ApiRecipesViewModel
import com.project.recipeapplication.viewModel.PersonalRecipeViewModel
import com.project.recipeapplication.viewModel.ShoppingViewModel


@Composable
fun BottomNavBar(apiRecipeViewModel : ApiRecipesViewModel,
                 personalRecipeViewModel: PersonalRecipeViewModel,
                 shoppingViewModel: ShoppingViewModel,
                 isDarkTheme : Boolean,
                 onToggleTheme : (Boolean) -> Unit)
{
    val navController = rememberNavController()
    var selectedNavItemIndex by rememberSaveable { mutableIntStateOf( 0) }

    val bottomNavItems = listOf(
    BottomNavBarItem(
        label = "Dashboard",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
        route = "dashboard"
    ),
    BottomNavBarItem(
        label = "Search",
        selectedIcon = Icons.Filled.Search,
        unSelectedIcon = Icons.Outlined.Search,
        route = "search"
    ),
    BottomNavBarItem(
        label = "Recipes",
        selectedIcon = Icons.Filled.List,
        unSelectedIcon = Icons.Outlined.List,
        route = "recipes"
    ),
    BottomNavBarItem(
        label = "Groceries",
        selectedIcon = Icons.Filled.ShoppingCart,
        unSelectedIcon = Icons.Outlined.ShoppingCart,
        route = "groceries"
    ))

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        label = {Text(text = item.label)},
                        selected = selectedNavItemIndex == index,
                        onClick = { selectedNavItemIndex = index
                                    navController.navigate(item.route) },
                        icon = { Icon(
                            imageVector = if (index == selectedNavItemIndex) {
                            item.selectedIcon
                            } else item.unSelectedIcon,
                        contentDescription = item.label
                        )}
                    )
                
                }

            }
        }


    ) {
        innerPadding -> Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = "dashboard") {
                composable("dashboard") {
                    Dashboard(navController = navController, isDarkTheme = isDarkTheme, onToggleTheme = onToggleTheme, viewModel = personalRecipeViewModel)
                }
                composable("search") {
                    Search(navController = navController, viewModel = apiRecipeViewModel)
                }
                composable("recipes?selectTab={selectTab}",
                    arguments = listOf(navArgument("selectTab") { type = NavType.IntType; defaultValue = 0 })
                ) { backStackEntry ->
                    val selectedTab = backStackEntry.arguments?.getInt("selectTab") ?: 0
                    Recipes(navController = navController, viewModel = personalRecipeViewModel, apiViewModel = apiRecipeViewModel, selectedTabIndex = selectedTab) }
                composable("groceries") {
                    ShoppingList(navController = navController, viewModel = shoppingViewModel)
                }
                composable("addRecipe") {
                    AddRecipe(navController = navController, viewModel = personalRecipeViewModel)
                }
                composable("apiRecipeInfo") {
                    ApiRecipeInfo(navController = navController, viewModel = apiRecipeViewModel)
                }
                composable("personalRecipeInfo") {
                    PersonalRecipeInfo(navController = navController, viewModel = personalRecipeViewModel)
                }
            }
        }
    }

}