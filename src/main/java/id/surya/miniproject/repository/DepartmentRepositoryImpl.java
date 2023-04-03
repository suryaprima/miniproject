package id.surya.miniproject.repository;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.model.mapper.DepartmentMapper;
import id.surya.miniproject.utils.IdRandom;
import id.surya.miniproject.utils.RandomString;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class DepartmentRepositoryImpl implements DepartmentRepository{
    private JdbcTemplate jdbc = new JdbcTemplate();
    private RandomString randomString = new IdRandom();
    private final String GET_ALL = "SELECT * FROM m_department";
    private final String SAVE = "INSERT INTO m_department(id_department, code_department, name_department) VALUES (?,?,?)";
    private final String GET_BY_ID = "SELECT * FROM m_department WHERE id_department = ?";
    private final String DELETE_BY_ID = "DELETE FROM m_department WHERE id_department = ?";
    private final String UPDATE_BY_ID = "UPDATE m_department SET code_department = ?, name_department = ? WHERE id_department = ?";

    public DepartmentRepositoryImpl() {
    }

    public DepartmentRepositoryImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();

        try{
            departments = jdbc.query(GET_ALL, new DepartmentMapper());
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }

        return departments;
    }

    @Override
    public Department save(Department department) {

        try {
            department.setIdDepartment(randomString.random());
            jdbc.update(SAVE, department.getIdDepartment(), department.getCodeDepartment(), department.getNameDepartment());
        } catch (DataAccessException exception) {
            throw new RuntimeException("Failed save department");
        }
        return department;
    }

    @Override
    public Optional<Department> findById(String id) {
        Department department = new Department();

        try{
            department = jdbc.queryForObject(GET_BY_ID,new  DepartmentMapper(),new Object[]{id});
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }

        return Optional.ofNullable(department);
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
    public void update(Department department, String id) {
        try {
            jdbc.update(UPDATE_BY_ID, department.getCodeDepartment(), department.getNameDepartment(), id);
        } catch (DataAccessException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
