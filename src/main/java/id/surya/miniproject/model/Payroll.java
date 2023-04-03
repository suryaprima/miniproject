package id.surya.miniproject.model;

public class Payroll {
    private String idPayroll;
    private String namePayroll;
    private Integer amountSalary;
    private Integer salaryCut;
    private String employee;

    public Payroll() {
    }

    public Payroll(String idPayroll, String namePayroll, Integer amountSalary, Integer salaryCut, String employee) {
        this.idPayroll = idPayroll;
        this.namePayroll = namePayroll;
        this.amountSalary = amountSalary;
        this.salaryCut = salaryCut;
        this.employee = employee;
    }

    public String getIdPayroll() {
        return idPayroll;
    }

    public void setIdPayroll(String idPayroll) {
        this.idPayroll = idPayroll;
    }

    public String getNamePayroll() {
        return namePayroll;
    }

    public void setNamePayroll(String namePayroll) {
        this.namePayroll = namePayroll;
    }

    public Integer getAmountSalary() {
        return amountSalary;
    }

    public void setAmountSalary(Integer amountSalary) {
        this.amountSalary = amountSalary;
    }

    public Integer getSalaryCut() {
        return salaryCut;
    }

    public void setSalaryCut(Integer salaryCut) {
        this.salaryCut = salaryCut;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "idPayroll='" + idPayroll + '\'' +
                ", namePayroll='" + namePayroll + '\'' +
                ", amountSalary=" + amountSalary +
                ", salaryCut=" + salaryCut +
                ", employee='" + employee + '\'' +
                '}';
    }
}
