using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace istv_backend.Data.Entity; 

[Table("person")]
public class Person {
    [Key, Column("person_id")]
    private int Id { get; set; }

    [Column("name")] 
    private string Name { get; set; }
    [Column("surname")]
    private string Surname { get; set; }
    [Column("unid")] 
    private string Unid { get; set; }
    [Column("pin")] 
    private string Pin { get; set; }
    [Column("birth_date")]
    private DateTime BirthDate { get; set; }
    [Column("gender")]
    private string Gender { get; set; }

}