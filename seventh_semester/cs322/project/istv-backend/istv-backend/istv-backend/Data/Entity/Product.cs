using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace istv_backend.Data.Entity; 

[Table("product")]
public class Product {
    [Key, Column("product_id")] public int Id { get; set; }
    [Column("product_code")] public string ProductCode { get; set; }
    [Column("full_name")] public string FullName { get; set; }
    [Column("country_of_origin")] public string CountryOfOrigin { get; set; }
    [Column("storage_quantity")] public string StorageQuantity { get; set; }
    [Column("price")] public string Price { get; set; }
    
}