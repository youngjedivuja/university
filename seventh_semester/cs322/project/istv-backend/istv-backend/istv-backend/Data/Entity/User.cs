using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace istv_backend.Data.Entity;

[Table("user")]
public class User : Auditable {
    [Key, Column("user_id")] 
    public int Id { get; set; }
    
    [Column("username")]
    public string Username { get; set; }

    [JsonIgnore] [Column("password")] 
    public string? Password { get; set; }

    [Column("email")]
    public string Email { get; set; }
    
}