package id.surya.miniproject.repository;

import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Payroll;
import id.surya.miniproject.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();
    User save(User user);
    User findById(String id);
    Employee findEmployee(String id);
    void delete(String id);
    void update(User user, String id);
}
