package id.surya.miniproject.repository;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.mapper.DepartmentMapper;
import id.surya.miniproject.model.mapper.EmployeeMapper;
import id.surya.miniproject.utils.IdRandom;
import id.surya.miniproject.utils.RandomString;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    private JdbcTemplate jdbc = new JdbcTemplate();
    private RandomString randomString = new IdRandom();
    private final String GET_ALL = "SELECT * FROM m_employee";
    private final String SAVE = "INSERT INTO m_employee(id_employee, name_employee, address_employee, startdate, enddate, id_department, is_active) VALUES (?,?,?,?,?,?,?)";
    private final String GET_BY_ID = "SELECT * FROM m_employee WHERE id_employee = ?";
    private final String GET_DEPARTMENT = "SELECT * FROM m_department WHERE id_department = ?";
    private final String DELETE_BY_ID = "DELETE FROM m_employee WHERE id_employee = ?";
    private final String UPDATE_BY_ID = "UPDATE m_employee SET name_employee = ?, address_employee = ?, startdate = ?, enddate = ?, id_department = ?, is_active = ? WHERE id_employee = ?";

    public EmployeeRepositoryImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try{
            employees = jdbc.query(GET_ALL, new EmployeeMapper());
        } catch (DataAccessException | NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        return employees;
    }

    @Override
    public Employee save(Employee employee) {
        try {
            employee.setIdEmployee(randomString.random());
            jdbc.update(SAVE, employee.getIdEmployee(), employee.getNameEmployee(), employee.getAddressEmployee(), employee.getStartDate(), employee.getEndDate(), employee.getDepartment(), employee.getActive());
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }
        return employee;
    }

    @Override
    public Employee findById(String id) {
        Employee employee = new Employee();

        try{
            employee = jdbc.queryForObject(GET_BY_ID,new EmployeeMapper(),new Object[]{id});
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }

        return employee;
    }

    @Override
    public Department findDepartment(String id) {
        Department department = new Department();

        try{
            department = jdbc.queryForObject(GET_DEPARTMENT,new DepartmentMapper(),new Object[]{id});
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }

        return department;
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
    public void update(Employee employee, String id) {
        try {
            jdbc.update(UPDATE_BY_ID, employee.getNameEmployee(), employee.getAddressEmployee(), employee.getStartDate(), employee.getEndDate(), employee.getDepartment(), employee.getActive(), id);
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
