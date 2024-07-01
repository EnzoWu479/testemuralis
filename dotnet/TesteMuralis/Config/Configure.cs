using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using TesteMuralis.Context;
using TesteMuralis.Exceptions;
using TesteMuralis.Repositories;
using TesteMuralis.Services.Viacep;
using TesteMuralis.Services.Viacep.Adapter;
using TesteMuralis.Services.Viacep.Application;

namespace TesteMuralis.Config
{
    public class Configure
    {
        public WebApplicationBuilder WebApplicationBuilder { get; set; }

        public Configure(WebApplicationBuilder webApplicationBuilder)
        {
            WebApplicationBuilder = webApplicationBuilder;
        }

        public void ConfigureServices()
        {
            WebApplicationBuilder.Services.AddDbContext<ClientContext>(opt => opt.UseSqlite("Data Source=data/Clientes.db"));
            WebApplicationBuilder.Services.AddScoped<ClienteService>();
            WebApplicationBuilder.Services.AddScoped<ClientRepository>();
            WebApplicationBuilder.Services.AddScoped<ViacepGateway, ViacepService>();
            WebApplicationBuilder.Services.AddControllers(config => {
                config.Filters.Add<GlobalExceptionFilter>();
            });
        }
    }
}