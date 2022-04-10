using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace istv_backend.Data.Entity;

[Table("order_product")]
public class OrderProduct {
    [Key, Column("order_product_id")] private int Id { get; set; }
    [Column("product_id")]
    [ForeignKey("Product")]
    public int ProductId { get; set; }

    [ForeignKey("ProductId")] public virtual Product Product { get; set; }
    
    [Column("order_id")]
    [ForeignKey("Order")]
    public int OrderId { get; set; }

    [ForeignKey("OrderId")] public virtual Order Order { get; set; }

    [Column("quantity")] private int quantity { get; set; }
}