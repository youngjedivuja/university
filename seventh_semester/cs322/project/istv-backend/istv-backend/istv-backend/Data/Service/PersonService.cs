using istv_backend.Data.Entity;

namespace istv_backend.Data.Service; 

public interface PersonService {
    Person Save(Person person);
}