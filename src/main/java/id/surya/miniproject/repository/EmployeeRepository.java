package id.surya.miniproject.repository;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> getAll();
    Employee save(Employee employee);
    Employee findById(String id);
    Department findDepartment(String id);
    void delete(String id);
    void update(Employee employee, String id);
}
