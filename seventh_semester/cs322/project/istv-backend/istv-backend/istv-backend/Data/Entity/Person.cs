using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace istv_backend.Data.Entity; 

[Table("person")]
public class Person {
    [Key, Column("person_id")]
    private int Id { get; set; }

    [Column("name")] 
    private string Name;
    [Column("surname")]
    private string Surname;
    [Column("unid")] 
    private string Unid;
    [Column("pin")] 
    private string Pin;
    [Column("birth_date")]
    private string BirthDate;
    [Column("gender")]
    private string Gender;

}