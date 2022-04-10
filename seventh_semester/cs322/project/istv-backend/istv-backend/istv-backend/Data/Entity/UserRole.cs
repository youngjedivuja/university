using System.ComponentModel.DataAnnotations.Schema;

namespace istv_backend.Data.Entity;

[Table("user_role")]
public class UserRole {
    [Column("role_id")]
    [ForeignKey("Role")]
    public int RoleId { get; set; }

    [ForeignKey("RoleId")] public virtual Role Role { get; set; }

    [Column("user_id")]
    [ForeignKey("User")]
    public int UserId { get; set; }

    [ForeignKey("UserId")] public virtual User User { get; set; }
}