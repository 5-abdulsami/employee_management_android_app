package com.example.employeemanagement.data

import kotlinx.coroutines.flow.Flow

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    //retrieving all employees through getAllEmployees() fun
    val allEmployees: Flow<List<Employee>> = employeeDao.getAllEmployees()

    //retrieving a specific employee based on his id
    fun getEmployeeById(id: Int): Flow<Employee> {
        return employeeDao.getEmployeeById(id)
    }

    //fun to add employee
    suspend fun addEmployee(employee: Employee) {
        employeeDao.insertEmployee(employee)
    }

    //fun to update employee info
    suspend fun updateEmployee(employee: Employee) {
        employeeDao.updateEmployee(employee)
    }

    //fun to delete employee
    suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employee)
    }
}