package id.surya.miniproject.repository;

import id.surya.miniproject.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {
    List<Department> getAll();
    Department save(Department department);
    Optional<Department> findById(String id);
    void delete(String id);
    void update(Department department, String id);
}
