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
    [Column("company_name")] private string CompanyName { get; set; }
    [Column("City")] private string City { get; set; }
}