package id.surya.miniproject.controller;

import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Leave;
import id.surya.miniproject.model.Payroll;
import id.surya.miniproject.service.PayrollService;
import id.surya.miniproject.utils.enums.TypeLeaveEnum;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PayrollController {
    private Scanner input = new Scanner(System.in);
    private PayrollService payrollService;

    public PayrollController() {
    }

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    public void menuPayroll(){
        while (true){
            System.out.println("1. Add Payroll");
            System.out.println("2. View By ID Payroll");
            System.out.println("3. View All Payroll");
            System.out.println("4. Delete Payroll");
            System.out.println("5. Update Payroll");
            System.out.println("x. Exit Application");
            System.out.print("Input menu: ");
            String menu = input.next();

            switch (menu){
                case "1":
                    addPayroll();
                    break;
                case "2":
                    viewPayrollId();
                    break;
                case "3":
                    viewPayroll();
                    break;
                case "4":
                    deletePayroll();
                    break;
                case "5":
                    updatePayroll();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Menu invalid, please input 1 to 5 to select menu and 'x' to exit ");
            }
        }
    }

    public void addPayroll(){
        System.out.println("-------------------");
        System.out.println("Menu Add Payroll");
        System.out.println("-------------------");

        System.out.print("Name Payroll : ");
        input.nextLine();
        String name = input.nextLine();
        while (!name.matches("[a-z,A-Z, +]{5,150}")){
            System.out.println("Can Alphabet, length text must min 5, max 150");
            System.out.print("Name Payroll : ");
            name = input.nextLine();
        }

        System.out.print("Amount Salary : ");
        String amountSalary = input.next();
        while (!amountSalary.matches("[0-9]{5,10}")){
            System.out.println("Input number 0-9");
            System.out.print("Amount Salary : ");
            amountSalary = input.next();
        }


        System.out.print("Cut Salary : ");
        String cutSalary = input.next();
        while (!cutSalary.matches("[0-9,-]{5,10}")){
            System.out.println("Input number 0-9");
            System.out.print("Cut Salary : ");
            cutSalary = input.next();
        }

        System.out.print("Id Employee : ");
        String idEmployee = input.next();
        Employee existEmployee = payrollService.viewEmployee(idEmployee);
        if (existEmployee.getIdEmployee() == null) {
            while (!idEmployee.matches("[a-z,A-Z,0-9,-]{36,150}")) {
                System.out.println("Only uuid");
                System.out.print("Id Employee : ");
                idEmployee = input.next();
            }
        }

        Payroll payroll = new Payroll();
        payroll.setNamePayroll(name);
        payroll.setAmountSalary(Integer.parseInt(amountSalary));
        payroll.setSalaryCut(Integer.parseInt(cutSalary));
        payroll.setEmployee(idEmployee);
        payrollService.create(payroll);
    }


    public void viewPayrollId(){
        System.out.println("---------------------");
        System.out.println("Menu View Payroll");
        System.out.println("---------------------");

        System.out.print("Id payroll : ");
        String id = input.next();
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}")){
            System.out.println("Only uuid");
            System.out.print("Id payroll : ");
            id = input.next();
        }

        Payroll payroll = payrollService.viewById(id);
        System.out.println(payroll);
    }

    public void viewPayroll(){
        System.out.println("---------------------");
        System.out.println("Menu View Payroll");
        System.out.println("---------------------");

        List<Payroll> payrolls = payrollService.viewAll();

        payrolls.stream()
                .sorted(Comparator.comparing(Payroll::getNamePayroll))
                .forEach(System.out::println);
    }

    public void deletePayroll(){
        System.out.println("---------------------");
        System.out.println("Menu Delete Payroll");
        System.out.println("---------------------");

        System.out.print("Id payroll : ");
        String id = input.next();
        if (id.equals("x")){
            return;
        }
        Payroll payroll = payrollService.viewById(id);
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}") || payroll.getIdPayroll() == null) {
            System.out.println("Only uuid or Id not found");
            System.out.print("Id payroll : ");
            id = input.next();
        }
        payrollService.delete(id);
        System.out.println("Success Deleted id "+ id);
    }

    public void updatePayroll(){
        System.out.println("-------------------");
        System.out.println("Menu Update Payroll");
        System.out.println("-------------------");

        System.out.print("Id Payroll : ");
        String id = input.next();
        if (id.equals("x")){
            return;
        }
        Payroll payroll = payrollService.viewById(id);
        System.out.println(payroll);
        while (!id.matches("[a-z,A-Z,0-9,-]{36,150}") || payroll.getIdPayroll() == null) {
            System.out.println("Only uuid or Id not found");
            System.out.print("Id Payroll : ");
            id = input.next();
        }

        System.out.print("Name Payroll : ");
        input.nextLine();
        String name = input.nextLine();
        while (!name.matches("[a-z,A-Z, +]{5,150}")){
            System.out.println("Can Alphabet, length text must min 5, max 150");
            System.out.print("Name Payroll : ");
            name = input.nextLine();
        }

        System.out.print("Amount Salary : ");
        String amountSalary = input.next();
        while (!amountSalary.matches("[0-9]{5,10}")){
            System.out.println("Input number 0-9");
            System.out.print("Amount Salary : ");
            amountSalary = input.next();
        }


        System.out.print("Cut Salary : ");
        String cutSalary = input.next();
        while (!cutSalary.matches("[0-9,-]{5,10}")){
            System.out.println("Input number 0-9");
            System.out.print("Cut Salary : ");
            cutSalary = input.next();
        }

        System.out.print("Id Employee : ");
        String idEmployee = input.next();
        Employee existEmployee = payrollService.viewEmployee(idEmployee);
        if (existEmployee.getIdEmployee() == null) {
            while (!idEmployee.matches("[a-z,A-Z,0-9,-]{36,150}")) {
                System.out.println("Only uuid");
                System.out.print("Id Employee : ");
                idEmployee = input.next();
            }
        }

        payroll.setNamePayroll(name);
        payroll.setAmountSalary(Integer.parseInt(amountSalary));
        payroll.setSalaryCut(Integer.parseInt(cutSalary));
        payroll.setEmployee(idEmployee);
        payrollService.update(payroll, id);
    }
}
