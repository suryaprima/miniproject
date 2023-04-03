package id.surya.miniproject.service;

import id.surya.miniproject.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> viewAll();
    Department create(Department department);
    Optional<Department> viewById(String id);
    void delete(String id);
    void update(Department department, String id);
}
