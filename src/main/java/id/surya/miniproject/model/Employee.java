package id.surya.miniproject.model;

import java.time.LocalDate;

public class Employee {
    private String idEmployee;
    private String nameEmployee;
    private String addressEmployee;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private String department;

    public Employee() {
    }

    public Employee(String idEmployee, String nameEmployee, String addressEmployee, LocalDate startDate, LocalDate endDate, Boolean isActive, String department) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.addressEmployee = addressEmployee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.department = department;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getAddressEmployee() {
        return addressEmployee;
    }

    public void setAddressEmployee(String addressEmployee) {
        this.addressEmployee = addressEmployee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee='" + idEmployee + '\'' +
                ", nameEmployee='" + nameEmployee + '\'' +
                ", addressEmployee='" + addressEmployee + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                ", department=" + department +
                '}';
    }
}
