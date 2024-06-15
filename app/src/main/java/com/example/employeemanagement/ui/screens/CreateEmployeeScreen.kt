package com.example.employeemanagement.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.employeemanagement.data.Employee
import com.example.employeemanagement.data.EmployeeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEmployeeScreen(navController: NavController, viewModel: EmployeeViewModel = viewModel()) {

    //initializing variables
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var designation by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    //shows Alert Dialog if its true
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            },
            title = { Text("Incomplete Information") },
            text = { Text("Please fill all fields to create an employee.") }
        )
    }

    Scaffold(
        topBar = {

            TopAppBar(
                title = { Text("Create Employee")
                }
            )

        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Enter the Details of New Employee",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

                InputTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = "First Name",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )
                InputTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = "Last Name",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )
                InputTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )
                InputTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = "Phone",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                InputTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = "Address",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )
                InputTextField(
                    value = designation,
                    onValueChange = { designation = it },
                    label = "Designation",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )
                InputTextField(
                    value = salary,
                    onValueChange = { salary = it },
                    label = "Salary",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )

                Button(
                    onClick = {
                        if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && address.isNotBlank() && designation.isNotBlank() && salary.isNotBlank()) {
                            val newEmployee = Employee(
                                firstName = firstName,
                                lastName = lastName,
                                email = email,
                                phone = phone,
                                address = address,
                                designation = designation,
                                salary = salary.toDoubleOrNull() ?: 0.0     //if salary is null, set initial value to 0
                            )
                            viewModel.addEmployee(newEmployee)
                            navController.navigate("employee_list")
                        } else {
                            showDialog = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    modifier = Modifier.fillMaxWidth().
                    background(color = Color.Black ,shape = ShapeDefaults.Medium)
                ) {
                    Text("Create Employee", style = MaterialTheme.typography.titleMedium, color = Color.White)
                }
            }
        }
    )
}

@Composable
fun InputTextField(value: String, onValueChange: (String) -> Unit, label: String, keyboardOptions: KeyboardOptions) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions
    )
}
