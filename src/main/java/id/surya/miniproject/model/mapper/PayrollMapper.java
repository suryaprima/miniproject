package id.surya.miniproject.model.mapper;

import id.surya.miniproject.model.Payroll;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PayrollMapper implements RowMapper<Payroll> {
    @Override
    public Payroll mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payroll payroll = new Payroll();
        payroll.setIdPayroll(rs.getString("id_payroll"));
        payroll.setNamePayroll(rs.getString("name_payroll"));
        payroll.setAmountSalary(rs.getInt("amount_salary"));
        payroll.setSalaryCut(rs.getInt("salary_cut"));
        payroll.setEmployee(rs.getString("id_employee"));
        return payroll;
    }
}
