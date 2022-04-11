using istv_backend.Data.Context;
using istv_backend.Data.Entity;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace istv_backend.Data.Service.Impl; 

public class OrderServiceImpl : OrderService {
    private readonly DataContext _context;
    private readonly OrderProductService _orderProductService;

    public OrderServiceImpl(DataContext context, OrderProductService orderProductService) {
        _context = context;
        _orderProductService = orderProductService;
    }

    public List<Order> GetAll() {
        return _context.Orders.Include("Buyer").ToList();
    }

    public Order GetById(int id) {
        return _context.Orders.Single(order => order.Id == id);
    }

    public int CalculateTotal(int id) {
        int total = 0;
        var orderProducts = _orderProductService.GetAllByOrderId(id);
        foreach (var op in orderProducts) {
            total += int.Parse(op.Product.Price) * op.Quantity;
        }

        return total;
    }

    public List<OrderProduct> GetAllOrderProductsByOrderId(int id) {
        return _orderProductService.GetAllByOrderId(id);
    }

    public Order toggleOrderStatus(int orderId, string status) {
        var order = GetById(orderId);
        order.OrderStatus = status;
        return Update(order);
    }
    
    public Order Save(Order order) {
        EntityEntry<Order> entry = _context.Orders.Add(order);
        _context.SaveChanges();
        return entry.Entity;
    }
    
    public Order Update(Order order) {
        Order? existingOrder = _context.Orders.Find(order.Id);
        if (existingOrder == null) {
            throw new ArgumentException("Order ne sme biti null");
        }

        EntityEntry<Order> entry = _context.Entry(existingOrder);
        entry.CurrentValues.SetValues(order);
        _context.SaveChanges();
        return entry.Entity;
    }
}