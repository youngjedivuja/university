namespace istv_backend.Data.dto; 

public class UserPersonEmployeeDTO {
    public String name {get; set;}
    public String surname {get; set;}
    public String unid {get; set;}
    public String pin {get; set;}
    public String gender {get; set;}
    public DateTime birthDate {get; set;}

    public String email {get; set;}
    public String username {get; set;}
    public String position {get; set;}

    public String bank {get; set;}
    public DateTime employmentStartDate {get; set;}

    public UserPersonEmployeeDTO() {
    }

    public UserPersonEmployeeDTO(string name, string surname, string unid, string pin, string gender, DateTime birthDate, string email, string username, string position, string bank, DateTime employmentStartDate) {
        this.name = name;
        this.surname = surname;
        this.unid = unid;
        this.pin = pin;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.position = position;
        this.bank = bank;
        this.employmentStartDate = employmentStartDate;
    }
}