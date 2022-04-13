using istv_backend.Data.Context;
using istv_backend.Data.Entity;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace istv_backend.Data.Service.Impl;

public class OrderProductServiceImpl: OrderProductService {
    private readonly DataContext _context;

    public OrderProductServiceImpl(DataContext context) {
        _context = context;
    }

    public List<OrderProduct> GetAllByOrderId(int id) {
        return _context.OrderProducts.Include("Product").Include("Order").Where(o => o.OrderId == id).ToList();
    }

    public OrderProduct Save(OrderProduct orderProduct) {
        EntityEntry<OrderProduct> entry = _context.OrderProducts.Add(orderProduct);
        _context.SaveChanges();
        return entry.Entity;
    }
}