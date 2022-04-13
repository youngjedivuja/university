using System.Security.Claims;
using istv_backend.Data.dto;
using istv_backend.Data.Entity;
using istv_backend.Data.Service;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;

namespace istv_backend.Controller;

[ApiController]
[Route("orders")]
public class OrderController : ControllerBase {
    private readonly OrderService _orderService;

    public OrderController(OrderService orderService) {
        _orderService = orderService;
    }
    protected int GetUserId()
    {
        return int.Parse(this.User.Claims.First(i => i.Type == "UserId").Value);
    }

    [HttpGet]
    public IActionResult GetAll() {
        return Ok(_orderService.GetAll());
    }

    [HttpGet("/{id}")]
    public IActionResult GetById(int id) {
        return Ok(_orderService.GetById(id));
    }

    [HttpGet("{id}/calculateTotal")]
    public IActionResult CalculateTotal(int id) {
        return Ok(_orderService.CalculateTotal(id));
    }

    [HttpGet("{id}/orderproducts")]
    public IActionResult GetAllOrderProductsByOrderId(int id) {
        return Ok(_orderService.GetAllOrderProductsByOrderId(id));
    }

    [HttpGet("{orderId}/toggleOrderStatus/{status}")]
    public IActionResult ToggleOrderStatus(int orderId, string status) {
        return Ok(_orderService.toggleOrderStatus(orderId, status));
    }

    [HttpGet("forBuyer/{username}")]
    public IActionResult GetAllByUsername(string username) {
        return Ok(_orderService.GetAllByUsername(username));
    }

    [HttpPost("saveOrderDTO/{username}")]
    public IActionResult SaveOrderDTO(string username, [FromBody] OrderDTO orderDto) {
        return Ok(_orderService.SaveOrderDTO(username, orderDto));
    }
}