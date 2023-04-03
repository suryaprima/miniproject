package id.surya.miniproject.model.mapper;

import id.surya.miniproject.model.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setIdDepartment(rs.getString("id_department"));
        department.setCodeDepartment(rs.getString("code_department"));
        department.setNameDepartment(rs.getString("name_department"));
        return department;
    }
}
