package id.surya.miniproject.controller;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.model.Employee;
import id.surya.miniproject.service.EmployeeService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    private Scanner input = new Scanner(System.in);
    private EmployeeService employeeService;
    public EmployeeController() {
    }

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void menuEmployee(){
        while (true){
            System.out.println("1. Add Employee");
            System.out.println("2. View By ID Employee");
            System.out.println("3. View All Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Update Employee");
            System.out.println("x. Exit Application");
            System.out.print("Input menu: ");
            String menu = input.next();

            switch (menu){
                case "1":
                    addEmp();
                    break;
                case "2":
                    viewEmpId();
                    break;
                case "3":
                    viewEmp();
                    break;
                case "4":
                    deleteEmp();
                    break;
                case "5":
                    updateEmp();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Menu invalid, please input 1 to 5 to select menu and 'x' to exit ");
            }
        }
    }

    public void addEmp(){
        System.out.println("-------------------");
        System.out.println("Menu Add Employee");
        System.out.println("-------------------");

        System.out.print("Name Employee : ");
        input.nextLine();
        String name = input.nextLine();
        while (!name.matches("[a-z,A-Z, +]{5,150}")){
            System.out.println("Can Alphabet, length text must min 5, max 30");
            System.out.print("Name Employee : ");
            name = input.nextLine();
        }

        System.out.print("Address Employee : ");
        String address = input.nextLine();
        while (!name.matches("[a-z,A-Z,0-9, +]{5,255}")){
            System.out.println("Can Alphabet, length text must min 5, max 30");
            System.out.print("Address Employee : ");
            address = input.nextLine();
        }

        System.out.print("Id Department : ");
        String idDepartment = input.next();
        Department existDepartment = employeeService.viewDepartment(idDepartment);
        if (existDepartment.equals(null)) {
            while (!idDepartment.matches("[a-z,A-Z,0-9,-]{36,150}")) {
                System.out.println("Only uuid");
                System.out.print("Id Department : ");
                idDepartment = input.next();
            }
        }

        Employee employee = new Employee();
        employee.setNameEmployee(name);
        employee.setAddressEmployee(address);
        employee.setStartDate(LocalDate.now());
        employee.setEndDate(null);
        employee.setDepartment(idDepartment);
        employee.setActive(true);
        employeeService.create(employee);
    }

    public void viewEmpId(){
        System.out.println("---------------------");
        System.out.println("Menu View Employee");
        System.out.println("---------------------");

        System.out.print("Id Employee : ");
        String id = input.next();
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}")){
            System.out.println("Only uuid");
            System.out.print("Id Employee : ");
            id = input.next();
        }

        Employee employee = employeeService.viewById(id);
        System.out.println(employee);
    }

    public void viewEmp(){
        System.out.println("---------------------");
        System.out.println("Menu View Employee");
        System.out.println("---------------------");

        List<Employee> employees = employeeService.viewAll();

        employees.stream()
                .sorted(Comparator.comparing(Employee::getNameEmployee))
                .forEach(System.out::println);
    }
    public void deleteEmp(){
        System.out.println("---------------------");
        System.out.println("Menu Delete Employee");
        System.out.println("---------------------");

        System.out.print("Id Employee : ");
        String id = input.next();
        if (id.equals("x")){
            return;
        }
        Employee employee = employeeService.viewById(id);
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}") || employee.getIdEmployee() == null) {
            System.out.println("Only uuid or Id not found");
            System.out.print("Id Employee : ");
            id = input.next();
        }
        employeeService.delete(id);
        System.out.println("Success Deleted id "+ id);
    }
    public void updateEmp() {
        System.out.println("---------------------");
        System.out.println("Menu Update Department");
        System.out.println("---------------------");

        System.out.print("Id Employee : ");
        String id = input.next();
        if (id.equals("x")){
            return;
        }
        Employee employee = employeeService.viewById(id);
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}") || employee.getIdEmployee() == null) {
            System.out.println("Only uuid or Id not found");
            System.out.print("Id Employee : ");
            id = input.next();
        }
        if (employee.getIdEmployee() != null) {
            System.out.print("Name Employee : ");
            input.nextLine();
            String name = input.nextLine();
            while (!name.matches("[a-z,A-Z, +]{5,150}")) {
                System.out.println("Can Alphabet, length text must min 5, max 30");
                System.out.print("Name Employee : ");
                name = input.nextLine();
            }

            System.out.print("Address Employee : ");
            String address = input.nextLine();
            while (!name.matches("[a-z,A-Z,0-9, +]{5,255}")) {
                System.out.println("Can Alphabet, length text must min 5, max 30");
                System.out.print("Address Employee : ");
                address = input.nextLine();
            }

            System.out.print("Id Department : ");
            String idDepartment = input.next();
            Department existDepartment = employeeService.viewDepartment(idDepartment);
            if (existDepartment.equals(null)) {
                while (!idDepartment.matches("[a-z,A-Z,0-9,-]{36,150}")) {
                    System.out.println("Only uuid");
                    System.out.print("Id Department : ");
                    idDepartment = input.next();
                }
            }

            System.out.print("Is Active : ");
            String isActive = input.next();
            while (!isActive.toLowerCase().matches("false")){
                System.out.println("Input must 'true' or 'false'");
                System.out.print("Is Active : ");
                isActive = input.next();
            }

            employee.setNameEmployee(name);
            employee.setAddressEmployee(address);
            employee.setActive(Boolean.valueOf(isActive));
            if (!employee.getActive()) {
                employee.setEndDate(LocalDate.now());
            }
            employee.setDepartment(idDepartment);
            employeeService.update(employee, id);
        }
    }
}
