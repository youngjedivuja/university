using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace istv_backend.Data.Entity; 

[Table("order")]
public class Order {
    [Key, Column("order_id")]
    public int Id { get; set; }
    [Column("buyer_id")]
    [ForeignKey("Buyer")]
    public int BuyerId { get; set; }

    [ForeignKey("BuyerId")] 
    public virtual Buyer Buyer { get; set; }
    [Column("delivery_address")] 
    public string DeliveryAddress { get; set; }
    [Column("order_status")]
    private string OrderStatus { get; set; }
}