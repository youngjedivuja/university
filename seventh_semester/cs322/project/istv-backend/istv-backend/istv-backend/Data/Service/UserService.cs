using istv_backend.Data.Entity;

namespace istv_backend.Data.Service; 

public interface UserService {
    User GetByUsername(string username);
    bool IsPasswordValid(string plain, string hashed);
    User GetById(int id);
    List<User> GetAll();
    List<Role> GetUserRole(User user);
    User Save(User user);
    User Update(User user);
}