package id.surya.miniproject.service;

import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.User;

import java.util.List;

public interface UserService {
    List<User> viewAll();
    User create(User user);
    User viewById(String id);
    Employee viewEmployee(String id);
    void delete(String id);
    void update(User user, String id);
}
