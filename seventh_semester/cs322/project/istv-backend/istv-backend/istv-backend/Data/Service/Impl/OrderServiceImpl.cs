using istv_backend.Data.Context;
using istv_backend.Data.dto;
using istv_backend.Data.Entity;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace istv_backend.Data.Service.Impl; 

public class OrderServiceImpl : OrderService {
    private readonly DataContext _context;
    private readonly OrderProductService _orderProductService;
    private readonly UserService _userService;
    private readonly BuyerService _buyerService;

    public OrderServiceImpl(DataContext context, OrderProductService orderProductService, UserService userService, BuyerService buyerService) {
        _context = context;
        _orderProductService = orderProductService;
        _userService = userService;
        _buyerService = buyerService;
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

    public List<Order> GetAllByUsername(string username) {
        var user = _userService.GetByUsername(username);
        return _context.Orders.Include("Buyer.User").Where(order => order.Buyer.User.Username == username).ToList();
    }

    public Order SaveOrderDTO(string username, OrderDTO orderDto) {
        var order = new Order();
        order.OrderStatus = ("Na cekanju");
        var buyer = _buyerService.GetByUsername(username);
        order.Buyer = buyer;
        order.BuyerId = buyer.Id;
        order.DeliveryAddress = orderDto.DeliveryAddress;
        var savedOrder = Save(order);

        foreach (var product in orderDto.products) {
            OrderProduct op = new OrderProduct();
            op.OrderId = savedOrder.Id;
            op.ProductId = product.Id;
            op.Quantity = 150;
            _orderProductService.Save(op);
        }

        return savedOrder;
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