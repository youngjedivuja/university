using istv_backend.Data.Entity;
using istv_backend.Data.Service;
using Microsoft.AspNetCore.Mvc;

namespace istv_backend.Controller; 

[ApiController]
[Route("products")]
public class ProductController : ControllerBase {
    private readonly ProductService _productService;

    public ProductController(ProductService productService) {
        _productService = productService;
    }

    [HttpGet]
    public IActionResult GetAll() {
        return Ok(_productService.GetAll());
    }
    
    [HttpGet("{id}")]
    public IActionResult GetById(int id) {
        return Ok(_productService.GetById(id));
    }
    
    [HttpPut("{id}/toggle")]
    public IActionResult UpdateRecordStatus(int id) {
        return Ok(_productService.UpdateRecordStatus(id));
    }

    [HttpPut]
    public IActionResult Update([FromBody] Product product) {
        return Ok(_productService.Update(product));
    }
    
    [HttpPost]
    public IActionResult Save([FromBody] Product product) {
        return Ok(_productService.Save(product));
    }
}