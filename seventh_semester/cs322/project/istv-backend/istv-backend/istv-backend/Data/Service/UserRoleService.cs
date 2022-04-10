using istv_backend.Data.Entity;

namespace istv_backend.Data.Service; 

public interface UserRoleService {
    List<Role> GetUserRoles(User user);
}