package id.surya.miniproject.model.mapper;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.model.Employee;
import id.surya.miniproject.service.DepartmentService;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeMapper implements RowMapper<Employee> {
    DepartmentService departmentService;

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setIdEmployee(rs.getString(1));
        employee.setNameEmployee(rs.getString(2));
        employee.setAddressEmployee(rs.getString(3));
        employee.setStartDate(rs.getDate(4).toLocalDate());
        if (rs.getDate(5) != null){
            employee.setEndDate(rs.getDate(5).toLocalDate());
        }
        employee.setDepartment(rs.getString(6));
        employee.setActive(rs.getBoolean(7));
        return employee;
    }
}
