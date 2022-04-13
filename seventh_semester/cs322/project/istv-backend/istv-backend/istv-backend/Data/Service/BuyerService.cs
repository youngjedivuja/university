using istv_backend.Data.Entity;

namespace istv_backend.Data.Service; 

public interface BuyerService {
    List<Buyer> GetAll();
    Buyer GetByUsername(string username);
}