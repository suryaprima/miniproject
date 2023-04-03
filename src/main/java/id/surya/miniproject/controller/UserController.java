package id.surya.miniproject.controller;

import id.surya.miniproject.model.*;
import id.surya.miniproject.model.User;
import id.surya.miniproject.model.User;
import id.surya.miniproject.service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserController {
    private Scanner input = new Scanner(System.in);
    private UserService userService;

    public UserController() {
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void menuUser(){
        while (true){
            System.out.println("1. Add User");
            System.out.println("2. View By ID User");
            System.out.println("3. View All User");
            System.out.println("4. Delete User");
            System.out.println("5. Update User");
            System.out.println("x. Exit Application");
            System.out.print("Input menu: ");
            String menu = input.next();

            switch (menu){
                case "1":
                    addUser();
                    break;
                case "2":
                    viewUserId();
                    break;
                case "3":
                    viewUser();
                    break;
                case "4":
                    deleteUser();
                    break;
                case "5":
                    updateUser();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Menu invalid, please input 1 to 5 to select menu and 'x' to exit ");
            }
        }
    }
    
    public void addUser(){
        System.out.println("-------------------");
        System.out.println("Menu Add User");
        System.out.println("-------------------");

        System.out.print("Email User : ");
        input.nextLine();
        String email = input.nextLine();
        while (!email.matches("^(.+)@(\\S+)$")){
            System.out.println("Input format user@domain.com");
            System.out.print("Email User : ");
            email = input.nextLine();
        }

        System.out.print("Id Employee : ");
        String idEmployee = input.next();
        Employee existEmployee = userService.viewEmployee(idEmployee);
        if (existEmployee.getIdEmployee() == null) {
            while (!idEmployee.matches("[a-z,A-Z,0-9,-]{36,150}")) {
                System.out.println("Only uuid");
                System.out.print("Id Employee : ");
                idEmployee = input.next();
            }
        }

        User user = new User();
        user.setEmailUser(email);
        user.setEmployee(idEmployee);
        userService.create(user);
    }
    
    public void viewUserId(){
        System.out.println("---------------------");
        System.out.println("Menu View User");
        System.out.println("---------------------");

        System.out.print("Id user : ");
        String id = input.next();
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}")){
            System.out.println("Only uuid");
            System.out.print("Id user : ");
            id = input.next();
        }

        User user = userService.viewById(id);
        System.out.println(user);
    }
    
    public void viewUser(){
        System.out.println("---------------------");
        System.out.println("Menu View User");
        System.out.println("---------------------");

        List<User> users = userService.viewAll();

        users.stream()
                .sorted(Comparator.comparing(User::getEmailUser))
                .forEach(System.out::println);
    }
    public void deleteUser(){
        System.out.println("---------------------");
        System.out.println("Menu Delete User");
        System.out.println("---------------------");

        System.out.print("Id user : ");
        String id = input.next();
        if (id.equals("x")){
            return;
        }
        User user = userService.viewById(id);
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}") || user.getIdUser() == null) {
            System.out.println("Only uuid or Id not found");
            System.out.print("Id user : ");
            id = input.next();
        }
        userService.delete(id);
        System.out.println("Success Deleted id "+ id);
    }
    public void updateUser(){
        System.out.println("-------------------");
        System.out.println("Menu Update User");
        System.out.println("-------------------");

        System.out.print("Id User : ");
        String id = input.next();
        if (id.equals("x")){
            return;
        }
        User user = userService.viewById(id);
        System.out.println(user);
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}") || user.getIdUser() == null) {
            System.out.println("Only uuid or Id not found");
            System.out.print("Id User : ");
            id = input.next();
        }

        System.out.print("Email User : ");
        input.nextLine();
        String email = input.nextLine();
        while (!email.matches("^(.+)@(\\S+)$")){
            System.out.println("Input format user@domain.com");
            System.out.print("Email User : ");
            email = input.nextLine();
        }

        System.out.print("Password User : ");
        String password = input.nextLine();
        Boolean bo = password.matches("[a-z,A-Z,0-9]{8,50}");
        System.out.println(bo);
        while (!password.matches("[a-z,A-Z,0-9]{8,50}")){
            System.out.println("Input min 8 to 50 character ");
            System.out.print("Password User : ");
            password = input.nextLine();
        }

        System.out.print("Id Employee : ");
        String idEmployee = input.next();
        Employee existEmployee = userService.viewEmployee(idEmployee);
        if (existEmployee.getIdEmployee() == null) {
            while (!idEmployee.matches("[a-z,A-Z,0-9,-]{36,150}")) {
                System.out.println("Only uuid");
                System.out.print("Id Employee : ");
                idEmployee = input.next();
            }
        }

        user.setEmailUser(email);
        user.setPassword(password);
        user.setEmployee(idEmployee);
        userService.update(user, id);
    }
}
