using istv_backend.Data.Entity;
using Microsoft.EntityFrameworkCore;

namespace istv_backend.Data.Context;

public class DataContext : DbContext {
    public DataContext(DbContextOptions<DataContext> options) : base(options) {
    }
    public DbSet<User> Users { get; set; }
    public DbSet<UserRole> UserRoles { get; set; }
    
    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder
            .Entity<UserRole>(
                eb =>
                {
                    eb.HasNoKey();
                });
    }
}