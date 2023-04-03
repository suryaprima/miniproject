package id.surya.miniproject.service;

import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Leave;

import java.util.List;

public interface LeaveService {
    List<Leave> viewAll();
    Leave create(Leave leave);
    Leave viewById(String id);
    Employee viewEmployee(String id);
    void delete(String id);
    void update(Leave leave, String id);
}
