package id.surya.miniproject.repository;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Leave;

import java.util.List;

public interface LeaveRepository {
    List<Leave> getAll();
    Leave save(Leave leave);
    Leave findById(String id);
    Employee findEmployee(String id);
    void delete(String id);
    void update(Leave leave, String id);
}
