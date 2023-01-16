import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'EmployeeDashboard';

  employeeDetails = null;

  employeeToUpdate = {
    id : "",
    name: "",
    surname : "",
    dob : "",
    joiningDate : "",
    state : "",
    pincode : ""
  };

  constructor(private employeeService: EmployeeService) {
    this.getEmployeesDetails();
  }
  register(registerForm: NgForm) {
    this.employeeService.registerEmployee(registerForm.value).subscribe(
      (resp) => {
        console.log(resp);
        registerForm.reset();
        this.getEmployeesDetails();
      },
      (err) => {
        console.log(err);
      }
    );
  }

  getEmployeesDetails() {
    this.employeeService.getEmployees().subscribe(
      (resp) => {
        console.log(resp);
        this.employeeDetails = resp;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  deleteEmployee(employee) {
    this.employeeService.deleteEmployee(employee.id).subscribe(
      (resp) => {
        console.log(resp);
        this.getEmployeesDetails();
      },
      (err) => {
         console.log(err);
      }
    );
  }

  edit(employee){
    this.employeeToUpdate = employee;
  }

  updateEmployee(){
    this.employeeService.updateEmployee(this.employeeToUpdate).subscribe(
      (resp) => {
        console.log(resp);
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
