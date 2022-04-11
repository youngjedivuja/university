using istv_backend.Data.Service;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace istv_backend.Controller; 

[ApiController]
[Route("buyers")]
[Authorize(Roles = "CEO")]
public class BuyerController : ControllerBase {
    private readonly BuyerService _buyerService;

    public BuyerController(BuyerService buyerService) {
        _buyerService = buyerService;
    }

    [HttpGet]
    public IActionResult Get() {
        return Ok(_buyerService.GetAll());
    }
}