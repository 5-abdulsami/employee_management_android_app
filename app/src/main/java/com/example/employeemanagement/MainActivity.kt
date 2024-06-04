package com.example.employeemanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.employeemanagement.ui.theme.EmployeeManagementTheme
import com.example.employeemanagement.ui.EmployeeApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeManagementTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    //Calling EmployeeApp
                    EmployeeApp()
                }
            }
        }
    }
}