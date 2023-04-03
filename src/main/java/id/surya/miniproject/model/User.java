package id.surya.miniproject.model;

public class User {
    private String idUser;
    private String emailUser;
    private String password;
    private String employee;

    public User() {
    }

    public User(String idUser, String emailUser, String password, String employee) {
        this.idUser = idUser;
        this.emailUser = emailUser;
        this.password = password;
        this.employee = employee;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser='" + idUser + '\'' +
                ", emailUser='" + emailUser + '\'' +
                ", password='" + password + '\'' +
                ", employee=" + employee +
                '}';
    }
}