using istv_backend.Data.Context;
using istv_backend.Data.Entity;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace istv_backend.Data.Service.Impl;

public class UserServiceImpl : UserService {

    private readonly DataContext _context;
    private readonly UserRoleService _userRoleService;

    public UserServiceImpl(DataContext context, UserRoleService userRoleService) {
        _context = context;
        _userRoleService = userRoleService;
    }

    public User GetByUsername(string username) {
        return _context.Users.Single(user => user.Username == username);
    }

    public bool IsPasswordValid(string plain, string hashed) {
        return BCrypt.Net.BCrypt.Verify(plain, hashed);
    }

    public User GetById(int id) {
        throw new NotImplementedException();
    }

    public List<User> GetAll() {
        throw new NotImplementedException();
    }

    public List<Role> GetUserRole(User user) {
        return _userRoleService.GetUserRoles(user);
    }

    public User Save(User user) {
        EntityEntry<User> entry = _context.Users.Add(user);
        _context.SaveChanges();
        return entry.Entity;
    }
}