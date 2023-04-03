package id.surya.miniproject.service;

import id.surya.miniproject.model.Employee;
import id.surya.miniproject.model.Leave;
import id.surya.miniproject.model.Payroll;
import id.surya.miniproject.repository.PayrollRepository;

import java.util.List;

public class PayrollServiceImpl implements PayrollService{
    private PayrollRepository payrollRepository;

    public PayrollServiceImpl(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    @Override
    public List<Payroll> viewAll() {
        List<Payroll> payrolls = payrollRepository.getAll();

        if (payrolls.isEmpty()){
            System.out.println("Payroll is empty...");
        }
        return payrolls;
    }

    @Override
    public Payroll create(Payroll payroll) {
        Payroll newLeave =  payrollRepository.save(payroll);
        return newLeave;
    }

    @Override
    public Payroll viewById(String id) {
        Payroll payroll = payrollRepository.findById(id);
        if (payroll.getIdPayroll() == null){
            System.out.println("Not found id "+id);
        }
        return payroll;
    }

    @Override
    public Employee viewEmployee(String id) {
        Employee employee = payrollRepository.findEmployee(id);
        if (employee.getIdEmployee() == null){
            System.out.println("Not found employee "+ id);
            return null;
        }
        return employee;
    }

    @Override
    public void delete(String id) {
        Payroll payroll = payrollRepository.findById(id);
        if (payroll.getIdPayroll() == null) {
            System.out.println("Not found id "+ id);
        }
        payrollRepository.delete(id);
    }

    @Override
    public void update(Payroll payroll, String id) {
        Payroll existPayroll = payrollRepository.findById(id);

        if (existPayroll.getIdPayroll() == null) {
            System.out.println("Not found id "+ id);
        }

        payrollRepository.update(payroll, id);
    }
}
