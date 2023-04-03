package id.surya.miniproject.repository;

import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Leave;
import id.surya.miniproject.model.Payroll;
import id.surya.miniproject.model.mapper.EmployeeMapper;
import id.surya.miniproject.model.mapper.LeaveMapper;
import id.surya.miniproject.model.mapper.PayrollMapper;
import id.surya.miniproject.utils.IdRandom;
import id.surya.miniproject.utils.RandomString;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class PayrollRepositoryImpl implements PayrollRepository{

    private JdbcTemplate jdbc = new JdbcTemplate();
    private RandomString randomString = new IdRandom();
    private final String GET_ALL = "SELECT * FROM t_payroll";
    private final String SAVE = "INSERT INTO t_payroll(id_payroll, name_payroll, amount_salary, salary_cut, id_employee) VALUES (?,?,?,?,?)";
    private final String GET_BY_ID = "SELECT * FROM t_payroll WHERE id_payroll = ?";
    private final String GET_EMPLOYEE = "SELECT * FROM m_employee WHERE id_employee = ?";
    private final String DELETE_BY_ID = "DELETE FROM t_payroll WHERE id_payroll = ?";
    private final String UPDATE_BY_ID = "UPDATE t_payroll SET name_payroll = ?, amount_salary = ?, salary_cut = ?, id_employee = ? WHERE id_payroll = ?";

    public PayrollRepositoryImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Payroll> getAll() {
        List<Payroll> payrolls = new ArrayList<>();
        try{
            payrolls = jdbc.query(GET_ALL, new PayrollMapper());
        } catch (DataAccessException | NullPointerException exception) {
            System.out.println(exception.getMessage());
        }
        return payrolls;
    }

    @Override
    public Payroll save(Payroll payroll) {
        try {
            payroll.setIdPayroll(randomString.random());
            jdbc.update(SAVE, payroll.getIdPayroll(), payroll.getNamePayroll(), payroll.getAmountSalary(), payroll.getSalaryCut(), payroll.getEmployee());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
        }
        return payroll;
    }

    @Override
    public Payroll findById(String id) {
        Payroll payroll = new Payroll();

        try{
            payroll = jdbc.queryForObject(GET_BY_ID,new PayrollMapper(),new Object[]{id});
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }

        return payroll;
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
    public void update(Payroll payroll, String id) {
        try {
            jdbc.update(UPDATE_BY_ID, payroll.getNamePayroll(), payroll.getAmountSalary(), payroll.getSalaryCut(), payroll.getEmployee(), id);
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
