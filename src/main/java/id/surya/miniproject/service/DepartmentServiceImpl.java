package id.surya.miniproject.service;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService{
    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl() {
    }

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> viewAll() {
        List<Department> departments = departmentRepository.getAll();

        if (departments.isEmpty()){
            System.out.println("Department is empty...");
        }
        return departments;
    }

    @Override
    public Department create(Department department) {
        Department newDepartment =  departmentRepository.save(department);
        return newDepartment;
    }

    @Override
    public Optional<Department> viewById(String id) {
        Department department = departmentRepository.findById(id).get();
        return Optional.ofNullable(department);
    }

    @Override
    public void delete(String id) {
        Department department = departmentRepository.findById(id).get();
        if (department.equals(null)) {
            throw new RuntimeException("Not found id "+ id);
        }
        departmentRepository.delete(id);
    }

    @Override
    public void update(Department department, String id) {
        Department existDepartment = departmentRepository.findById(id).get();

        if (existDepartment.equals(null)) {
            throw new RuntimeException("Not found id "+ id);
        }

        departmentRepository.update(department, id);
    }
}
