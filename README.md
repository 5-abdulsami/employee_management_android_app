An Employee Management Android App made in Kotlin using Jetpack Compose, Room for persistent data storage.

Contains 3 screens:

- Main Screen:
    - Displays the current employees and their designation
    - Has an action button in tab bar allowing user to enter a new employee
  
- Crete Employee Screen:
    - Textfields to enter the Employee Name, Email, Phone Number, Address, Designation and Salary
    - Prompts user to fill all fields through Alert Dialog if any field left empty
    - Creates the employee on tapping the Create Employee button and stores the info at local db
      
- Update / Delete Employee Screen:
    - Displays current info of employee and allows user to update the info or delete the employee
    - Prompts user to fill all fields through Alert Dialog if any field left empty
    - Displays an Alert Dialog to ask for confirmation of user to delete employee 
      
