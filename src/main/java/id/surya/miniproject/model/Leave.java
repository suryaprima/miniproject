package id.surya.miniproject.model;

import id.surya.miniproject.utils.enums.TypeLeaveEnum;

import java.time.LocalDate;

public class Leave {
    private String idLeave;
    private TypeLeaveEnum typeLeave;
    private String nameLeave;
    private LocalDate startDate;
    private LocalDate endDate;
    private String employee;

    public Leave() {
    }

    public Leave(String idLeave, TypeLeaveEnum typeLeave, String nameLeave, LocalDate startDate, LocalDate endDate, String employee) {
        this.idLeave = idLeave;
        this.typeLeave = typeLeave;
        this.nameLeave = nameLeave;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = employee;
    }

    public String getIdLeave() {
        return idLeave;
    }

    public void setIdLeave(String idLeave) {
        this.idLeave = idLeave;
    }

    public TypeLeaveEnum getTypeLeave() {
        return typeLeave;
    }

    public void setTypeLeave(TypeLeaveEnum typeLeave) {
        this.typeLeave = typeLeave;
    }

    public String getNameLeave() {
        return nameLeave;
    }

    public void setNameLeave(String nameLeave) {
        this.nameLeave = nameLeave;
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

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "idLeave='" + idLeave + '\'' +
                ", typeLeave=" + typeLeave +
                ", nameLeave='" + nameLeave + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", employee='" + employee + '\'' +
                '}';
    }
}
