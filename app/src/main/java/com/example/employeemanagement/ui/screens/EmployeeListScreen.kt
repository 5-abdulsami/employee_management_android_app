package com.example.employeemanagement.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.employeemanagement.data.Employee
import com.example.employeemanagement.data.EmployeeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeListScreen(navController: NavController, viewModel: EmployeeViewModel = viewModel()) {

    //initialize it as an empty list
    val employees = viewModel.employees.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Employee Management") },
                actions = {
                    IconButton(onClick = { navController.navigate("create_employee") }) {
                        Icon(Icons.Default.AddCircle, contentDescription = "Add Employee")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            //builds the dynamic list of employee items
            LazyColumn {
                items(employees.value) { employee ->
                    EmployeeItem(employee) {
                        navController.navigate("update_delete_employee/${employee.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun EmployeeItem(employee: Employee, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            //column view showing first, last name and designation of employee
            Text(text = "${employee.firstName} ${employee.lastName}", style = MaterialTheme.typography.titleLarge, color = Color.White)
            Text(text = employee.designation, style = MaterialTheme.typography.bodyLarge, color = Color.LightGray)
        }
    }
}
