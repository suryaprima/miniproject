package id.surya.miniproject.service;

import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Payroll;
import id.surya.miniproject.model.User;
import id.surya.miniproject.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> viewAll() {
        List<User> users = userRepository.getAll();

        if (users.isEmpty()){
            System.out.println("Payroll is empty...");
        }
        return users;
    }

    @Override
    public User create(User user) {
        User newLeave =  userRepository.save(user);
        return newLeave;
    }

    @Override
    public User viewById(String id) {
        User user = userRepository.findById(id);
        if (user.getIdUser() == null){
            System.out.println("Not found id "+id);
        }
        return user;
    }

    @Override
    public Employee viewEmployee(String id) {
        Employee employee = userRepository.findEmployee(id);
        if (employee.getIdEmployee() == null){
            System.out.println("Not found employee "+ id);
            return null;
        }
        return employee;    }

    @Override
    public void delete(String id) {
        User user = userRepository.findById(id);
        if (user.getIdUser() == null) {
            System.out.println("Not found id "+ id);
        }
        userRepository.delete(id);
    }

    @Override
    public void update(User user, String id) {
        User existUser = userRepository.findById(id);

        if (existUser.getIdUser() == null) {
            System.out.println("Not found id "+ id);
        }

        userRepository.update(user, id);
    }
}
