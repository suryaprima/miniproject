package id.surya.miniproject.service;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.model.Employee;
import id.surya.miniproject.repository.DepartmentRepository;
import id.surya.miniproject.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> viewAll() {
        List<Employee> employees = employeeRepository.getAll();

        if (employees.isEmpty()){
            System.out.println("Employee is empty...");
        }
        return employees;
    }

    @Override
    public Employee create(Employee employee) {
        Employee newEmployee =  employeeRepository.save(employee);
        return newEmployee;
    }

    @Override
    public Employee viewById(String id) {
        Employee employee = employeeRepository.findById(id);
        if (employee.getIdEmployee() == null){
            System.out.println("Not found id "+id);
        }
        return employee;
    }

    @Override
    public Department viewDepartment(String id) {
        Department department = employeeRepository.findDepartment(id);
        if (department.getIdDepartment() == null){
            System.out.println("Not found department "+ id);
            return null;
        }
        return department;
    }

    @Override
    public void delete(String id) {
        Employee employee = employeeRepository.findById(id);
        if (employee.getIdEmployee() == null) {
            System.out.println("Not found id "+ id);
        }
        employeeRepository.delete(id);
    }

    @Override
    public void update(Employee employee, String id) {
        Employee existEmployee = employeeRepository.findById(id);

        if (existEmployee.getIdEmployee() == null) {
            System.out.println("Not found id "+ id);
        }

        employeeRepository.update(employee, id);
    }
}
