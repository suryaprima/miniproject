package id.surya.miniproject.repository;

import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Payroll;
import id.surya.miniproject.model.User;
import id.surya.miniproject.model.mapper.EmployeeMapper;
import id.surya.miniproject.model.mapper.PayrollMapper;
import id.surya.miniproject.model.mapper.UserMapper;
import id.surya.miniproject.utils.IdRandom;
import id.surya.miniproject.utils.PasswordRandom;
import id.surya.miniproject.utils.RandomString;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    private JdbcTemplate jdbc;
    private RandomString idRandom = new IdRandom();
    private RandomString passRandom = new PasswordRandom();

    private final String GET_ALL = "SELECT * FROM m_user";
    private final String SAVE = "INSERT INTO m_user(id_user, email_user, password_user, id_employee) VALUES (?,?,?,?)";
    private final String GET_BY_ID = "SELECT * FROM m_user WHERE id_user = ?";
    private final String GET_EMPLOYEE = "SELECT * FROM m_employee WHERE id_employee = ?";
    private final String DELETE_BY_ID = "DELETE FROM m_user WHERE id_user = ?";
    private final String UPDATE_BY_ID = "UPDATE m_user SET email_user = ?, password_user = ?, id_employee = ? WHERE id_user = ?";

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try{
            users = jdbc.query(GET_ALL, new UserMapper());
        } catch (DataAccessException | NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        return users;
    }

    @Override
    public User save(User user) {
        try {
            user.setIdUser(idRandom.random());
            user.setPassword(passRandom.random());
            jdbc.update(SAVE, user.getIdUser(), user.getEmailUser(), user.getPassword(), user.getEmployee());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
        }
        return user;
    }

    @Override
    public User findById(String id) {
        User user = new User();

        try{
            user = jdbc.queryForObject(GET_BY_ID,new UserMapper(),new Object[]{id});
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }

        return user;
    }

    @Override
    public Employee findEmployee(String id) {
        Employee employee = new Employee();

        try{
            employee = jdbc.queryForObject(GET_EMPLOYEE, new EmployeeMapper(),new Object[]{id});
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }

        return employee;
    }

    @Override
    public void delete(String id) {
        try {
            jdbc.update(DELETE_BY_ID, id);
        } catch (DataAccessException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void update(User user, String id) {
        try {
            jdbc.update(UPDATE_BY_ID, user.getEmailUser(), user.getPassword(), user.getEmployee(), id);
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
