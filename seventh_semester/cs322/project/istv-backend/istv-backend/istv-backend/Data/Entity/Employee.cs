using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace istv_backend.Data.Entity;

[Table("employee")]
public class Employee {
    [Key, Column("employee_id")] public int Id { get; set; }

    [Column("user_id")]
    [ForeignKey("User")]
    public int UserId { get; set; }

    [ForeignKey("UserId")] public virtual User User { get; set; }

    [Column("position")] public string Position { get; set; }
    [Column("bank")] public string Bank { get; set; }
    [Column("employment_start_date")] public DateTime EmploymentStartDate { get; set; }
    [Column("employment_end_date")] public DateTime EmploymentEndDate { get; set; }
    
}