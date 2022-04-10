using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace istv_backend.Data.Entity;

[Table("role")]
public class Role : Auditable {
    [Key, Column("role_id")] 
    public int Id { get; set; }
    [Column("role_name")] 
    public string Name { get; set; }
}