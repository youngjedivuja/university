using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace istv_backend.Data.Entity; 

[Table("buyer")]
public class Buyer {
    [Key, Column("buyer_id")] public int Id { get; set; }
    [Column("user_id")]
    [ForeignKey("User")]
    public int UserId { get; set; }

    [ForeignKey("UserId")] public virtual User User { get; set; }
    [Column("company_name")] public string CompanyName { get; set; }
    [Column("City")] public string City { get; set; }
}