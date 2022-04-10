using istv_backend.Data.Context;
using istv_backend.Data.Entity;
using Microsoft.EntityFrameworkCore;

namespace istv_backend.Data.Service.Impl; 

public class UserRoleServiceImpl : UserRoleService {
    
    private readonly DataContext _context;

    public UserRoleServiceImpl(DataContext context) {
        _context = context;
    }

    public List<Role> GetUserRoles(User user) {
        var userRoles = _context.UserRoles.Include("User").Include("Role").Where(userRoles => userRoles.UserId == user.Id).ToList();
        List<Role> roles = new List<Role>();
        foreach (var userRole in userRoles) {
            roles.Add(userRole.Role);
        }
        return roles;
    }
}