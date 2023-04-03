package id.surya.miniproject.service;

import id.surya.miniproject.model.Department;
import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Leave;
import id.surya.miniproject.repository.EmployeeRepository;
import id.surya.miniproject.repository.LeaveRepository;

import java.util.List;

public class LeaveServiceImpl implements LeaveService{
    private LeaveRepository leaveRepository;

    public LeaveServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @Override
    public List<Leave> viewAll() {
        List<Leave> leaves = leaveRepository.getAll();

        if (leaves.isEmpty()){
            System.out.println("Leave is empty...");
        }
        return leaves;
    }

    @Override
    public Leave create(Leave leave) {
        Leave newLeave =  leaveRepository.save(leave);
        return newLeave;
    }

    @Override
    public Leave viewById(String id) {
        Leave leave = leaveRepository.findById(id);
        if (leave.getIdLeave() == null){
            System.out.println("Not found id "+id);
        }
        return leave;
    }

    @Override
    public Employee viewEmployee(String id) {
        Employee employee = leaveRepository.findEmployee(id);
        if (employee.getIdEmployee() == null){
            System.out.println("Not found employee "+ id);
            return null;
        }
        return employee;
    }

    @Override
    public void delete(String id) {
        Leave leave = leaveRepository.findById(id);
        if (leave.getIdLeave() == null) {
            System.out.println("Not found id "+ id);
        }
        leaveRepository.delete(id);
    }

    @Override
    public void update(Leave leave, String id) {
        Leave existLeave = leaveRepository.findById(id);

        if (existLeave.getIdLeave() == null) {
            System.out.println("Not found id "+ id);
        }

        leaveRepository.update(leave, id);
    }
}
