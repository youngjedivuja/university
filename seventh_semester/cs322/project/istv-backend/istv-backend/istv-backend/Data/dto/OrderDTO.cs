using istv_backend.Data.Entity;

namespace istv_backend.Data.dto; 

public class OrderDTO {
    public String DeliveryAddress { get; set; }
    public List<Product> products { get; set; }
}