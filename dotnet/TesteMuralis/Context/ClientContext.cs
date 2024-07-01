using Microsoft.EntityFrameworkCore;
using TesteMuralis.Models;

namespace TesteMuralis.Context
{
    public class ClientContext : DbContext
    {
        public ClientContext(DbContextOptions<ClientContext> options) : base(options)
        {
        }

        public DbSet<Cliente> Clients { get; set; }
    }
}