package com.example.employeemanagement.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

//Data Access Object Instance
@Dao
interface EmployeeDao {

    //provides methods that app uses to interact with data in db table

    @Query("SELECT * FROM employee")
    fun getAllEmployees(): Flow<List<Employee>>

    @Query("SELECT * FROM employee WHERE id = :id")
    fun getEmployeeById(id: Int): Flow<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)
}