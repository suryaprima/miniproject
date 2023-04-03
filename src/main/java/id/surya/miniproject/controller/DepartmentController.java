package id.surya.miniproject.controller;


import id.surya.miniproject.model.Department;
import id.surya.miniproject.repository.DepartmentRepository;
import id.surya.miniproject.repository.DepartmentRepositoryImpl;
import id.surya.miniproject.service.DepartmentService;
import id.surya.miniproject.service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DepartmentController {
    private Scanner input = new Scanner(System.in);
    private DepartmentService departmentService;

    public DepartmentController() {
    }

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void menuDepartment(){
        while (true){
            System.out.println("1. Add Department");
            System.out.println("2. View By ID Department");
            System.out.println("3. View All Department");
            System.out.println("4. Delete Department");
            System.out.println("5. Update Department");
            System.out.println("x. Exit Application");
            System.out.print("Input menu: ");
            String menu = input.next();

            switch (menu){
                case "1":
                    addDept();
                    break;
                case "2":
                    viewDeptId();
                    break;
                case "3":
                    viewDept();
                    break;
                case "4":
                    deleteDept();
                    break;
                case "5":
                    updateDept();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Menu invalid, please input 1 to 5 to select menu and 'x' to exit ");
            }
        }
    }

    public void addDept(){
        System.out.println("-------------------");
        System.out.println("Menu Add Department");
        System.out.println("-------------------");

        System.out.print("Code Department : ");
        String code = input.next();
        while (!code.matches("[a-z,A-Z,0-9]{5}")){
            System.out.println("Can Alphabet, Number, Alphanumeric, length text must 5");
            System.out.print("Code Department : ");
            code = input.next();
        }

        input.nextLine();
        System.out.print("Name Department : ");
        String name = input.nextLine();
        while (!name.matches("[a-z,A-Z, +]{5,30}")){
            System.out.println("Can Alphabet, length text must min 5, max 30");
            System.out.print("Name Department : ");
            name = input.nextLine();
        }

        Department department = new Department(null,code,name);
        departmentService.create(department);
    }

    public void viewDept(){
        System.out.println("---------------------");
        System.out.println("Menu View Department");
        System.out.println("---------------------");

        List<Department> departments = departmentService.viewAll();
        departments.stream()
                .sorted(Comparator.comparing(Department::getCodeDepartment))
                .forEach(System.out::println);
    }

    public void viewDeptId(){
        System.out.println("---------------------");
        System.out.println("Menu View Department");
        System.out.println("---------------------");

        System.out.print("Id Department : ");
        String id = input.next();
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}")){
            System.out.println("Only uuid");
            System.out.print("Id Department : ");
            id = input.next();
        }

        Department department = departmentService.viewById(id).get();
        System.out.println(department);
    }

    public void deleteDept(){
        System.out.println("---------------------");
        System.out.println("Menu Delete Department");
        System.out.println("---------------------");

        System.out.print("Id Department : ");
        String id = input.next();
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}")){
            System.out.println("Only uuid");
            System.out.print("Id Department : ");
            id = input.next();
        }
        departmentService.delete(id);
        System.out.println("Success Deleted id "+ id);
    }

    public void updateDept(){
        System.out.println("---------------------");
        System.out.println("Menu Update Department");
        System.out.println("---------------------");

        System.out.print("Id Department : ");
        String id = input.next();
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}")){
            System.out.println("Only uuid");
            System.out.print("Id Department : ");
            id = input.next();
        }
        Department department = departmentService.viewById(id).get();
        if (department != null){
            System.out.print("Code Department : ");
            String code = input.next();
            while (!code.matches("[a-z,A-Z,0-9]{5}")){
                System.out.println("Can Alphabet, Number, Alphanumeric, length text must 5");
                System.out.print("Code Department : ");
                code = input.next();
            }

            input.nextLine();
            System.out.print("Name Department : ");
            String name = input.nextLine();
            while (!name.matches("[a-z,A-Z, +]{5,30}")){
                System.out.println("Can Alphabet, length text must min 5, max 30");
                System.out.print("Name Department : ");
                name = input.nextLine();
            }
            department.setCodeDepartment(code);
            department.setNameDepartment(name);
            departmentService.update(department,id);
            System.out.println("Success Updated id "+ id);
        }
    }
}
