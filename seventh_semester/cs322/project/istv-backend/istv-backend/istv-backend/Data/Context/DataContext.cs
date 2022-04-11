using istv_backend.Data.Entity;
using Microsoft.EntityFrameworkCore;

namespace istv_backend.Data.Context;

public class DataContext : DbContext {
    public DataContext(DbContextOptions<DataContext> options) : base(options) {
    }

    public DbSet<User> Users { get; set; }
    public DbSet<UserRole> UserRoles { get; set; }
    public DbSet<Buyer> Buyers { get; set; }
    public DbSet<Employee> Employees { get; set; }
    public DbSet<Person> Persons { get; set; }
    public DbSet<Product> Products { get; set; }
    public DbSet<Order> Orders { get; set; }
    public DbSet<OrderProduct> OrderProducts { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder) {
        modelBuilder
            .Entity<UserRole>(
                eb => { eb.HasNoKey(); });
    }
}