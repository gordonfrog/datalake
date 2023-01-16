import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  API = 'http://localhost:9090';

  public registerEmployee(employeeData: any) {
    return this.http.post(this.API + '/registerEmployee', employeeData)
  }

  public getEmployees() {
    return this.http.get(this.API + '/getEmployees')
  }

  public deleteEmployee(id) {
    return this.http.delete(this.API + '/deleteEmployee?id=' + id)
  }
  public updateEmployee(employee){
    return this.http.put(this.API + '/updateEmployee', employee);
  }

}
