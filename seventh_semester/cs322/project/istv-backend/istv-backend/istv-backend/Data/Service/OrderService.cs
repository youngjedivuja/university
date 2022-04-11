using istv_backend.Data.Entity;

namespace istv_backend.Data.Service; 

public interface OrderService {
    List<Order> GetAll();
    Order GetById(int id);
    int CalculateTotal(int id);
    List<OrderProduct> GetAllOrderProductsByOrderId(int id);
    Order toggleOrderStatus(int orderId, string status);
}