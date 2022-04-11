using istv_backend.Data.Context;
using istv_backend.Data.Entity;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace istv_backend.Data.Service.Impl; 

public class PersonServiceImpl : PersonService {
    private readonly DataContext _context;

    public PersonServiceImpl(DataContext context) {
        _context = context;
    }

    public Person Save(Person person) {
        EntityEntry<Person> entry = _context.Persons.Add(person);
        _context.SaveChanges();
        return entry.Entity;
    }
}