using istv_backend.Data.Entity;

namespace istv_backend.Data.Service; 

public interface OrderProductService {
    List<OrderProduct> GetAllByOrderId(int id);
}