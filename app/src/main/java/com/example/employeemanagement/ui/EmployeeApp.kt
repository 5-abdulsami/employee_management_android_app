package com.example.employeemanagement.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.employeemanagement.ui.screens.EmployeeListScreen
import com.example.employeemanagement.ui.screens.CreateEmployeeScreen
import com.example.employeemanagement.ui.screens.UpdateDeleteEmployeeScreen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text

@Composable
fun EmployeeApp() {
    //Initializing Navigation Controller
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "employee_list") {
        //enlisting screens
        composable("employee_list") { EmployeeListScreen(navController) }
        composable("create_employee") { CreateEmployeeScreen(navController) }
        composable(
            "update_delete_employee/{employeeId}",
            arguments = listOf(navArgument("employeeId") { type = androidx.navigation.NavType.IntType })
        ) { backStackEntry ->
            val employeeId = backStackEntry.arguments?.getInt("employeeId") ?: -1
            if (employeeId != -1) {
                UpdateDeleteEmployeeScreen(navController, employeeId.toString())
            } else {
                //handling any error case if encountered
                InvalidEmployeeIdAlert(navController)
            }
        }
    }
}

@Composable
fun InvalidEmployeeIdAlert(navController: NavHostController) {
    AlertDialog(
        onDismissRequest = {
            navController.navigate("employee_list") {
                popUpTo("employee_list") { inclusive = true }
            }
        },
        confirmButton = {
            Button(onClick = {
                navController.navigate("employee_list") {
                    popUpTo("employee_list") { inclusive = true }
                }
            }) {
                Text("OK")
            }
        },
        title = {
            Text(text = "Invalid Employee ID")
        },
        text = {
            Text("The employee ID provided is not valid.")
        }
    )
}
