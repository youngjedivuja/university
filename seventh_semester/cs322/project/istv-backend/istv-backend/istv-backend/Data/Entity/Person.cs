using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace istv_backend.Data.Entity; 

[Table("person")]
public class Person : Auditable{
    [Key, Column("person_id")]
    public int Id { get; set; }

    [Column("name")] 
    public string Name { get; set; }
    [Column("surname")]
    public string Surname { get; set; }
    [Column("unid")] 
    public string Unid { get; set; }
    [Column("pin")] 
    public string Pin { get; set; }
    [Column("birth_date")]
    public DateTime BirthDate { get; set; }
    [Column("gender")]
    public string Gender { get; set; }

}