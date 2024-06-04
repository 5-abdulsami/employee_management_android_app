package com.example.employeemanagement.ui.screens

import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.example.employeemanagement.data.EmployeeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDeleteEmployeeScreen(
    navController: NavController,
    employeeId: String,
    viewModel: EmployeeViewModel = viewModel()              //passing the viewModel() method to all screens to get reference of EmployeeViewModel
) {
    val employeeFlow = viewModel.getEmployeeById(employeeId.toInt())
    val employee by employeeFlow.collectAsState(initial = null)

    //variables to store boolean values of AlertDialogs
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showIncompleteDialog by remember { mutableStateOf(false) }
    var showInvalidSalaryDialog by remember { mutableStateOf(false) }

    if (employee != null) {
        //employee credentials

        //Used not-null assertion operator to assert that the nullable expression isn't null
        var firstName by remember { mutableStateOf(TextFieldValue(employee!!.firstName)) }
        var lastName by remember { mutableStateOf(TextFieldValue(employee!!.lastName)) }
        var email by remember { mutableStateOf(TextFieldValue(employee!!.email)) }
        var phone by remember { mutableStateOf(TextFieldValue(employee!!.phone)) }
        var address by remember { mutableStateOf(TextFieldValue(employee!!.address)) }
        var designation by remember { mutableStateOf(TextFieldValue(employee!!.designation)) }
        var salary by remember { mutableStateOf(TextFieldValue(employee!!.salary.toString())) }

        //Alert Dialogs shown if true
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                        viewModel.deleteEmployee(employee!!)
                        navController.navigate("employee_list")
                    }) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDeleteDialog = false }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Delete Employee", color = Color.White) },
                text = { Text("Are you sure you want to delete this employee?") }
            )
        }

        if (showIncompleteDialog) {
            AlertDialog(
                onDismissRequest = { showIncompleteDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = { showIncompleteDialog = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Incomplete Information", color = Color.White) },
                text = { Text("Please fill all fields to update the employee.") }
            )
        }

        if (showInvalidSalaryDialog) {
            AlertDialog(
                onDismissRequest = { showInvalidSalaryDialog = false },
                confirmButton = {
                    TextButton(onClick = { showInvalidSalaryDialog = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Invalid Salary", color = Color.White) },
                text = { Text("Please enter a valid salary.") }
            )
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Update/Delete Employee", color = Color.White) }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name") },
                    modifier = Modifier.fillMaxWidth(),
                )
                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") },
                    modifier = Modifier.fillMaxWidth(),
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                )
                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                )
                TextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Address") },
                    modifier = Modifier.fillMaxWidth(),
                )
                TextField(
                    value = designation,
                    onValueChange = { designation = it },
                    label = { Text("Designation") },
                    modifier = Modifier.fillMaxWidth(),
                )
                TextField(
                    value = salary,
                    onValueChange = { salary = it },
                    label = { Text("Salary") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = salary.text.isBlank() || salary.text.toDoubleOrNull() == null
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            if (firstName.text.isNotBlank() && lastName.text.isNotBlank() && email.text.isNotBlank() && phone.text.isNotBlank() && address.text.isNotBlank() && designation.text.isNotBlank() && salary.text.isNotBlank()
                            ) {
                                val salaryValue = salary.text.toDoubleOrNull()
                                if (salaryValue != null) {
                                    //used not-null assertion operator to assert that employee isn't null
                                    val updatedEmployee = employee!!.copy(
                                        firstName = firstName.text,
                                        lastName = lastName.text,
                                        email = email.text,
                                        phone = phone.text,
                                        address = address.text,
                                        designation = designation.text,
                                        salary = salaryValue
                                    )
                                    viewModel.updateEmployee(updatedEmployee)
                                    navController.navigate("employee_list")
                                } else {
                                    showInvalidSalaryDialog = true
                                }
                            } else {
                                showIncompleteDialog = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier
                            .weight(1f)
                            .background(color = Color.White, shape = ShapeDefaults.Large)
                    ) {
                        Text("Update Employee", style = MaterialTheme.typography.titleMedium, color = Color.Black)
                    }

                    Button(
                        onClick = {
                            showDeleteDialog = true
                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier
                            .weight(1f)
                            .background(color = Color.White, shape = ShapeDefaults.Large)
                    ) {
                        Text(
                            "Delete Employee",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    } else {
        // If employee isn't found
        Text("Employee not found", modifier = Modifier.padding(18.dp))
    }
}
