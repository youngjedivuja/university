using istv_backend.Data.dto;
using istv_backend.Data.Entity;
using istv_backend.Data.Service;
using Microsoft.AspNetCore.Mvc;

namespace istv_backend.Controller; 

[ApiController]
[Route("employees")]
public class EmployeeController : ControllerBase {
    private readonly EmployeeService _employeeService;

    public EmployeeController(EmployeeService employeeService) {
        _employeeService = employeeService;
    }

    [HttpGet]
    public IActionResult GetAll() {
        return Ok(_employeeService.GetAll());
    }

    [HttpGet("{id}")]
    public IActionResult GetById(int id) {
        return Ok(_employeeService.GetById(id));
    }

    [HttpPut("{id}/toggle")]
    public IActionResult UpdateRecordStatus(int id) {
        return Ok(_employeeService.UpdateRecordStatus(id));
    }

    [HttpPut]
    public IActionResult Update([FromBody] Employee employee) {
        return Ok(_employeeService.Update(employee));
    }

    [HttpPost, Route("saveEmployeeDTO")]
    public IActionResult SaveDTO([FromBody] UserPersonEmployeeDTO userPersonEmployeeDTO) {
        return Ok(_employeeService.SaveUserPersonEmployeeDTO(userPersonEmployeeDTO));
    }
    
    
    
    
}