using System.Security.Authentication;
using System.Security.Claims;
using istv_backend.Data.Entity;
using istv_backend.Data.Service;
using istv_backend.Security;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace istv_backend.Controller;

[Authorize]
[ApiController]
public class AuthController : ControllerBase {
    private readonly UserService _userService;
    private readonly JWTAuthManager _jwtAuthManager;

    public AuthController(UserService userService, JWTAuthManager jwtAuthManager) {
        _jwtAuthManager = jwtAuthManager;
        _userService = userService;
    }

    [AllowAnonymous]
    [HttpPost("login")]
    public ActionResult Login([FromBody] LoginHttpRequest request) {
        if (!ModelState.IsValid) {
            return BadRequest();
        }

        User user = _userService.GetByUsername(request.Username);

        if (!_userService.IsPasswordValid(request.Password, user.Password)) {
            throw new AuthenticationException("Username or password not valid");
        }

        var roles = _userService.GetUserRole(user);
        var roleStrings = new List<string>();
        foreach (var role in roles) {
            roleStrings.Add(role.Name);
        }

        var jwtResult = _jwtAuthManager.GenerateTokens(user, roleStrings);
        return Ok(jwtResult);
    }
}