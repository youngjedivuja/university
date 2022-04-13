using istv_backend.Data.Context;
using istv_backend.Data.Entity;
using Microsoft.EntityFrameworkCore;

namespace istv_backend.Data.Service.Impl;

public class BuyerServiceImpl : BuyerService {
    private readonly DataContext _context;

    public BuyerServiceImpl(DataContext context) {
        _context = context;
    }

    public List<Buyer> GetAll() {
        return _context.Buyers.ToList();
    }

    public Buyer GetByUsername(string username) {
        return _context.Buyers.Include("User").Single(buyer => buyer.User.Username == username);
    }
}