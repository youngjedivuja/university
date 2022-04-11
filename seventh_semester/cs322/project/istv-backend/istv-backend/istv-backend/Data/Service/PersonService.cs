using istv_backend.Data.Entity;

namespace istv_backend.Data.Service; 

public interface PersonService {
    Person Save(Person person);
    Person GetByPin(string pin);
    Person Update(Person person);
}