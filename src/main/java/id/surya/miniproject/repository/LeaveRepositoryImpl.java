package id.surya.miniproject.repository;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Leave;
import id.surya.miniproject.model.mapper.DepartmentMapper;
import id.surya.miniproject.model.mapper.EmployeeMapper;
import id.surya.miniproject.model.mapper.LeaveMapper;
import id.surya.miniproject.utils.IdRandom;
import id.surya.miniproject.utils.RandomString;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class LeaveRepositoryImpl implements LeaveRepository{
    private JdbcTemplate jdbc = new JdbcTemplate();
    private RandomString randomString = new IdRandom();
    private final String GET_ALL = "SELECT * FROM t_leave";
    private final String SAVE = "INSERT INTO t_leave(id_leave, type_leave, name_leave, start_date, end_date, id_employee) VALUES (?,?,?,?,?,?)";
    private final String GET_BY_ID = "SELECT * FROM t_leave WHERE id_leave = ?";
    private final String GET_EMPLOYEE = "SELECT * FROM m_employee WHERE id_employee = ?";
    private final String DELETE_BY_ID = "DELETE FROM t_leave WHERE id_leave = ?";
    private final String UPDATE_BY_ID = "UPDATE t_leave SET type_leave = ?, name_leave = ?, start_date = ?, end_date = ?, id_employee = ? WHERE id_leave = ?";

    public LeaveRepositoryImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Leave> getAll() {
        List<Leave> leaves = new ArrayList<>();
        try{
            leaves = jdbc.query(GET_ALL, new LeaveMapper());
        } catch (DataAccessException | NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        return leaves;
    }

    @Override
    public Leave save(Leave leave) {
        try {
            leave.setIdLeave(randomString.random());
            jdbc.update(SAVE, leave.getIdLeave(), leave.getTypeLeave().name(), leave.getNameLeave(), leave.getStartDate(), leave.getEndDate(), leave.getEmployee());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
        }
        System.out.println(leave);
        return leave;
    }

    @Override
    public Leave findById(String id) {
        Leave leave = new Leave();

        try{
            leave = jdbc.queryForObject(GET_BY_ID,new LeaveMapper(),new Object[]{id});
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }

        return leave;
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
    public void update(Leave leave, String id) {
        try {
            jdbc.update(UPDATE_BY_ID, leave.getTypeLeave().name(), leave.getNameLeave(), leave.getStartDate(), leave.getEndDate(), leave.getEmployee(), id);
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
