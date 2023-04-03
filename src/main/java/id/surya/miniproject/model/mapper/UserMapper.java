package id.surya.miniproject.model.mapper;

import id.surya.miniproject.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setIdUser(rs.getString("id_user"));
        user.setEmailUser(rs.getString("email_user"));
        user.setPassword(rs.getString("password_user"));
        user.setEmployee(rs.getString("id_employee"));
        return user;
    }
}
