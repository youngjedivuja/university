using istv_backend.Data.Service;
using Microsoft.AspNetCore.Mvc;

namespace istv_backend.Controller; 

[ApiController]
[Route("orders")]
public class OrderController : ControllerBase{
    private readonly OrderService _orderService;

    public OrderController(OrderService orderService) {
        _orderService = orderService;
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
    public IActionResult toggleOrderStatus(int orderId, string status) {
        return Ok(_orderService.toggleOrderStatus(orderId, status));
    }
}