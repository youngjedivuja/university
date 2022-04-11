using istv_backend.Data.Entity;

namespace istv_backend.Data.Service; 

public interface ProductService {
    List<Product> GetAll();
    Product Save(Product product);
    Product Update(Product product);
    Product UpdateRecordStatus(int id);
    Product GetById(int id);
}