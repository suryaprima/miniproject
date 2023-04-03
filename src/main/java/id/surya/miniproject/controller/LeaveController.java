package id.surya.miniproject.controller;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Leave;
import id.surya.miniproject.service.LeaveService;
import id.surya.miniproject.utils.enums.TypeLeaveEnum;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LeaveController {
    private Scanner input = new Scanner(System.in);
    private LeaveService leaveService;

    public LeaveController() {
    }
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    public void menuLeave(){
        while (true){
            System.out.println("1. Add Leave");
            System.out.println("2. View By ID Leave");
            System.out.println("3. View All Leave");
            System.out.println("4. Delete Leave");
            System.out.println("5. Update Leave");
            System.out.println("x. Exit Application");
            System.out.print("Input menu: ");
            String menu = input.next();

            switch (menu){
                case "1":
                    addLeave();
                    break;
                case "2":
                    viewLeaveId();
                    break;
                case "3":
                    viewLeave();
                    break;
                case "4":
                    deleteLeave();
                    break;
                case "5":
                    updateLeave();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Menu invalid, please input 1 to 5 to select menu and 'x' to exit ");
            }
        }
    }

    public void addLeave(){
        System.out.println("-------------------");
        System.out.println("Menu Add Leave");
        System.out.println("-------------------");

        System.out.print("Type Leave : ");
        String type = input.next();

        while (!type.toUpperCase().matches("SPECIAL") & !type.toUpperCase().matches("ANNUAL") & !type.toUpperCase().matches("UNPAID")){
            System.out.println("Input 'SPECIAL' or 'ANNUAL' or 'UNPAID'");
            System.out.print("Type Leave : ");
            type = input.next();
        }

        System.out.print("Name Leave : ");
        input.nextLine();
        String name = input.nextLine();
        while (!name.matches("[a-z,A-Z, +]{5,150}")){
            System.out.println("Can Alphabet, length text must min 5, max 150");
            System.out.print("Name Leave : ");
            name = input.nextLine();
        }

        System.out.print("Start Date : ");
        String startDate = input.next();
        while (!startDate.matches("[0-9,-]{10}")){
            System.out.println("Input format yyyy-MM-dd");
            System.out.print("Start Date : ");
            startDate = input.next();
        }
        System.out.print("End Date : ");
        String endDate = input.next();
        while (!endDate.matches("[0-9,-]{10}")){
            System.out.println("Input format yyyy-MM-dd");
            System.out.print("End Date : ");
            endDate = input.next();
        }
        System.out.print("Id Employee : ");
        String idEmployee = input.next();
        Employee existEmployee = leaveService.viewEmployee(idEmployee);
        if (existEmployee.getIdEmployee() == null) {
            while (!idEmployee.matches("[a-z,A-Z,0-9,-]{36,150}")) {
                System.out.println("Only uuid");
                System.out.print("Id Employee : ");
                idEmployee = input.next();
            }
        }

        Leave leave = new Leave();
        leave.setTypeLeave(TypeLeaveEnum.valueOf(type.toUpperCase()));
        leave.setNameLeave(name);
        leave.setStartDate(LocalDate.parse(startDate));
        leave.setEndDate(LocalDate.parse(endDate));
        leave.setEmployee(idEmployee);
        leaveService.create(leave);
    }

    public void viewLeaveId(){
        System.out.println("---------------------");
        System.out.println("Menu View Leave");
        System.out.println("---------------------");

        System.out.print("Id Leave : ");
        String id = input.next();
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}")){
            System.out.println("Only uuid");
            System.out.print("Id Leave : ");
            id = input.next();
        }

        Leave leave = leaveService.viewById(id);
        System.out.println(leave);
    }

    public void viewLeave(){
        System.out.println("---------------------");
        System.out.println("Menu View Leave");
        System.out.println("---------------------");

        List<Leave> leaves = leaveService.viewAll();

        leaves.stream()
                .sorted(Comparator.comparing(Leave::getStartDate).reversed())
                .forEach(System.out::println);
    }

    public void deleteLeave(){
        System.out.println("---------------------");
        System.out.println("Menu Delete Leave");
        System.out.println("---------------------");

        System.out.print("Id Leave : ");
        String id = input.next();
        if (id.equals("x")){
            return;
        }
        Leave leave = leaveService.viewById(id);
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}") || leave.getIdLeave() == null) {
            System.out.println("Only uuid or Id not found");
            System.out.print("Id Leave : ");
            id = input.next();
        }
        leaveService.delete(id);
        System.out.println("Success Deleted id "+ id);
    }

    public void updateLeave(){
        System.out.println("-------------------");
        System.out.println("Menu Update Leave");
        System.out.println("-------------------");

        System.out.print("Id Leave : ");
        String id = input.next();
        if (id.equals("x")){
            return;
        }
        Leave leave = leaveService.viewById(id);
        System.out.println(leave);
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}") || leave.getIdLeave() == null) {
            System.out.println("Only uuid or Id not found");
            System.out.print("Id Leave : ");
            id = input.next();
        }

        System.out.print("Type Leave : ");
        String type = input.next();
        while (!type.toUpperCase().matches("SPECIAL") & !type.toUpperCase().matches("ANNUAL") & !type.toUpperCase().matches("UNPAID")){
            System.out.println("Input 'special' or 'annual' or 'unpaid'");
            System.out.print("Type Leave : ");
            type = input.next();
        }

        System.out.print("Name Leave : ");
        input.nextLine();
        String name = input.nextLine();
        while (!name.matches("[a-z,A-Z, +]{5,150}")){
            System.out.println("Can Alphabet, length text must min 5, max 150");
            System.out.print("Name Leave : ");
            name = input.nextLine();
        }

        System.out.print("Start Date : ");
        String startDate = input.next();
        while (!startDate.matches("[0-9,-]{10}")){
            System.out.println("Input format yyyy-MM-dd");
            System.out.print("Start Date : ");
            startDate = input.next();
        }
        System.out.print("End Date : ");
        String endDate = input.next();
        while (!endDate.matches("[0-9,-]{10}")){
            System.out.println("Input format yyyy-MM-dd");
            System.out.print("End Date : ");
            endDate = input.next();
        }
        System.out.print("Id Employee : ");
        String idEmployee = input.next();
        Employee existEmployee = leaveService.viewEmployee(idEmployee);
        if (existEmployee.equals(null)) {
            while (!idEmployee.matches("[a-z,A-Z,0-9,-]{36,150}")) {
                System.out.println("Only uuid");
                System.out.print("Id Employee : ");
                idEmployee = input.next();
            }
        }

        leave.setTypeLeave(TypeLeaveEnum.valueOf(type.toUpperCase()));
        leave.setNameLeave(name);
        leave.setStartDate(LocalDate.parse(startDate));
        leave.setEndDate(LocalDate.parse(endDate));
        leave.setEmployee(idEmployee);
        leaveService.update(leave, id);
    }

}
