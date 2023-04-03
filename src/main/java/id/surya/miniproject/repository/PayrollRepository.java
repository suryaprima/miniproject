package id.surya.miniproject.repository;

import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Leave;
import id.surya.miniproject.model.Payroll;

import java.util.List;

public interface PayrollRepository {
    List<Payroll> getAll();
    Payroll save(Payroll payroll);
    Payroll findById(String id);
    Employee findEmployee(String id);
    void delete(String id);
    void update(Payroll payroll, String id);
}
