using istv_backend.Data.dto;
using istv_backend.Data.Entity;

namespace istv_backend.Data.Service; 

public interface EmployeeService {
    List<Employee> GetAll();
    Employee GetById(int id);
    Employee UpdateRecordStatus(int id);
    Employee Save(Employee employee);
    Employee Update(Employee employee);
    Employee SaveUserPersonEmployeeDTO(UserPersonEmployeeDTO userPersonEmployeeDto);
    Employee UpdateUserPersonEmployeeDTO(UserPersonEmployeeDTO userPersonEmployeeDto);
    Employee GetByUserId(int userId);
}