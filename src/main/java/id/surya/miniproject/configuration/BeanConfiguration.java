package id.surya.miniproject.configuration;

import id.surya.miniproject.controller.*;
import id.surya.miniproject.repository.*;
import id.surya.miniproject.service.*;
import id.surya.miniproject.utils.IdRandom;
import id.surya.miniproject.utils.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Scanner;

@Configuration
public class BeanConfiguration {
    @Value("${driver}")
    private String dbDriver;
    @Value("${url}")
    private String dbUrl;
    @Value("${dbUser}")
    private String dbUser;
    @Value(("${dbPassword}"))
    private String dbPassword;

    @Bean
    RandomString randomId(){
        return new IdRandom();
    }

    @Bean
    public MainController getMainController(){
        return new MainController(
                getDepartmentController(),
                getEmployeeController(),
                getLeaveController(),
                getPayrollController(),
                getUserController()
        );
    }
    @Bean
    public DepartmentController getDepartmentController(){
        return new DepartmentController(getDepartmentService());
    }
    @Bean
    public DepartmentService getDepartmentService(){
        return new DepartmentServiceImpl(getDepartmentRepository());
    }
    @Bean
    public DepartmentRepository getDepartmentRepository(){
        return new DepartmentRepositoryImpl(dataSource());
    }

    public EmployeeController getEmployeeController(){
        return new EmployeeController(getEmployeeService());
    }
    @Bean
    public EmployeeService getEmployeeService(){
        return new EmployeeServiceImpl(getEmployeeRepository());
    }
    @Bean
    public EmployeeRepository getEmployeeRepository(){
        return new EmployeeRepositoryImpl(dataSource());
    }
    @Bean
    public LeaveController getLeaveController(){
        return new LeaveController(getLeaveService());
    }
    @Bean
    public LeaveService getLeaveService(){
        return new LeaveServiceImpl(getLeaveRepository());
    }
    @Bean
    public LeaveRepository getLeaveRepository(){
        return new LeaveRepositoryImpl(dataSource());
    }
    @Bean
    public PayrollController getPayrollController(){
        return new PayrollController(getPayrollService());
    }
    @Bean
    public PayrollService getPayrollService(){
        return new PayrollServiceImpl(getPayrollRepository());
    }
    @Bean
    public PayrollRepository getPayrollRepository(){
        return new PayrollRepositoryImpl(dataSource());
    }
    @Bean
    public UserController getUserController(){
        return new UserController(getUserService());
    }
    @Bean
    public UserService getUserService(){
        return new UserServiceImpl(getUserRepository());
    }
    @Bean
    public UserRepository getUserRepository(){
        return new UserRepositoryImpl(dataSource());
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(dbDriver);

        return dataSource;
    }
}
