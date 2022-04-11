using istv_backend.Data.Context;
using istv_backend.Data.dto;
using istv_backend.Data.Entity;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace istv_backend.Data.Service.Impl;

public class EmployeeServiceImpl : EmployeeService {
    private readonly DataContext _context;
    private readonly PersonService _personService;
    private readonly UserService _userService;

    public EmployeeServiceImpl(DataContext context, PersonService personService, UserService userService) {
        _context = context;
        _personService = personService;
        _userService = userService;
    }

    public List<Employee> GetAll() {
        return _context.Employees.Include("UserId.PersonId").ToList();
    }

    public Employee GetById(int id) {
        return _context.Employees.Include("UserId.PersonId").Single(employee => employee.Id == id);
    }

    public Employee UpdateRecordStatus(int id) {
        var employee = GetById(id);
        employee.RecordStatus = employee.RecordStatus > 0 ? 0 : 1;
        return Update(employee);
    }

    public Employee Update(Employee employee) {
        Employee? existingEmployee = _context.Employees.Find(employee.Id);
        if (existingEmployee == null) {
            throw new ArgumentException("Employee ne sme biti null");
        }

        EntityEntry<Employee> entry = _context.Entry(existingEmployee);
        entry.CurrentValues.SetValues(employee);
        _context.SaveChanges();
        return entry.Entity;
    }

    public Employee SaveUserPersonEmployeeDTO(UserPersonEmployeeDTO userPersonEmployeeDto) {
        Person person = new Person();
        person.BirthDate = userPersonEmployeeDto.birthDate;
        person.Gender = userPersonEmployeeDto.gender;
        person.Name = userPersonEmployeeDto.name;
        person.Surname = userPersonEmployeeDto.surname;
        person.Unid = userPersonEmployeeDto.unid;
        person.Pin = userPersonEmployeeDto.pin;
        person = _personService.Save(person);

        User user = new User();
        user.PersonId = person;
        user.PersonFk = person.Id;
        user.Email = userPersonEmployeeDto.email;
        user.Username = userPersonEmployeeDto.username;
        user.Password = BCrypt.Net.BCrypt.HashPassword(user.Username + "123");
        user = _userService.Save(user);

        Employee employee = new Employee();
        employee.UserFk = user.Id;
        employee.UserId = user;
        employee.Bank = userPersonEmployeeDto.bank;
        employee.Position = userPersonEmployeeDto.position;
        employee.EmploymentStartDate = userPersonEmployeeDto.employmentStartDate;
        
        EntityEntry<Employee> entry = _context.Employees.Add(employee);
        _context.SaveChanges();
        return entry.Entity;
    }

    public Employee Save(Employee employee) {
        EntityEntry<Employee> entry = _context.Employees.Add(employee);
        _context.SaveChanges();
        return entry.Entity;
    }
}