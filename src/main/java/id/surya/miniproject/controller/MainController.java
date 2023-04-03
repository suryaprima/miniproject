package id.surya.miniproject.controller;


import java.util.Scanner;

public class MainController {
    private Scanner input = new Scanner(System.in);
    private DepartmentController departmentController;
    private EmployeeController employeeController;
    private LeaveController leaveController;
    private PayrollController payrollController;
    private UserController userController;

    public MainController() {
    }

    public MainController(DepartmentController departmentController, EmployeeController employeeController,
                          LeaveController leaveController, PayrollController payrollController, UserController userController) {
        this.departmentController = departmentController;
        this.employeeController = employeeController;
        this.leaveController = leaveController;
        this.payrollController = payrollController;
        this.userController = userController;
    }

    public void run(){
        System.out.println("Application Human Resource Information System (HRIS)");
        while (true){
//            System.out.println("Menu Login");
//            System.out.println("Email : ");
//            String email = input.next();
//            System.out.println("Password : ");
//            String password = input.nextLine();
            System.out.println("1. Management Department");
            System.out.println("2. Management Employee");
            System.out.println("3. Management Leave");
            System.out.println("4. Management Payroll");
            System.out.println("5. Management User");
            System.out.println("x. Exit Application");
            System.out.print("Input menu: ");
            String menu = input.next();

            switch (menu){
                case "1":
                    departmentController.menuDepartment();
                    break;
                case "2":
                    employeeController.menuEmployee();
                    break;
                case "3":
                    leaveController.menuLeave();
                    break;
                case "4":
                    payrollController.menuPayroll();
                    break;
                case "5":
                    userController.menuUser();
                    break;
                case "x":
                    System.exit(0);
                default:
                    System.out.println("Menu invalid, please input 1 to 5 to select menu and 'x' to exit ");
            }


        }

    }
}
