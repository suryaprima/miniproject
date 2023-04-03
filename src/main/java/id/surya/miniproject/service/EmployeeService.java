package id.surya.miniproject.service;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> viewAll();
    Employee create(Employee employee);
    Employee viewById(String id);
    Department viewDepartment(String id);
    void delete(String id);
    void update(Employee employee, String id);
}
