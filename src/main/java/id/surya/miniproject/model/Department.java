package id.surya.miniproject.model;

public class Department {
    private String idDepartment;
    private String codeDepartment;
    private String nameDepartment;

    public Department() {
    }

    public Department(String idDepartment, String codeDepartment, String nameDepartment) {
        this.idDepartment = idDepartment;
        this.codeDepartment = codeDepartment;
        this.nameDepartment = nameDepartment;
    }

    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getCodeDepartment() {
        return codeDepartment;
    }

    public void setCodeDepartment(String codeDepartment) {
        this.codeDepartment = codeDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    @Override
    public String toString() {
        return "Department{" +
                "idDepartment='" + idDepartment + '\'' +
                ", codeDepartment='" + codeDepartment + '\'' +
                ", nameDepartment='" + nameDepartment + '\'' +
                '}';
    }
}
