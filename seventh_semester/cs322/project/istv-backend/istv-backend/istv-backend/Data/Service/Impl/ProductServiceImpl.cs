using istv_backend.Data.Context;
using istv_backend.Data.Entity;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace istv_backend.Data.Service.Impl; 

public class ProductServiceImpl : ProductService{
    private readonly DataContext _context;

    public ProductServiceImpl(DataContext context) {
        _context = context;
    }

    public List<Product> GetAll() {
        return _context.Products.ToList();
    }

    public Product Save(Product product) {
        EntityEntry<Product> entry = _context.Products.Add(product);
        _context.SaveChanges();
        return entry.Entity;
    }

    public Product Update(Product product) {
        Product? existingProduct = _context.Products.Find(product.Id);
        if (existingProduct == null) {
            throw new ArgumentException("Product ne sme biti null");
        }

        EntityEntry<Product> entry = _context.Entry(existingProduct);
        entry.CurrentValues.SetValues(product);
        _context.SaveChanges();
        return entry.Entity;
    }

    public Product UpdateRecordStatus(int id) {
        var product = GetById(id);
        product.RecordStatus = product.RecordStatus > 0 ? 0 : 1;
        return Update(product);
    }

    public Product GetById(int id) {
        return _context.Products.Single(product => product.Id == id);
    }
}