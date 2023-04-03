package id.surya.miniproject.model.mapper;

import id.surya.miniproject.model.Leave;
import id.surya.miniproject.utils.enums.TypeLeaveEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaveMapper implements RowMapper<Leave> {
    @Override
    public Leave mapRow(ResultSet rs, int rowNum) throws SQLException {
        Leave leave = new Leave();
        leave.setIdLeave(rs.getString("id_leave"));
        leave.setTypeLeave(TypeLeaveEnum.valueOf(rs.getString("type_leave").toUpperCase()));
        leave.setNameLeave(rs.getString("name_leave"));
        leave.setEmployee(rs.getString("id_employee"));
        leave.setStartDate(rs.getDate("start_date").toLocalDate());
        leave.setEndDate(rs.getDate("end_date").toLocalDate());
        return leave;
    }
}
