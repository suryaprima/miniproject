package id.surya.miniproject.service;

import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Payroll;

import java.util.List;

public interface PayrollService {
    List<Payroll> viewAll();
    Payroll create(Payroll payroll);
    Payroll viewById(String id);
    Employee viewEmployee(String id);
    void delete(String id);
    void update(Payroll payroll, String id);
}
