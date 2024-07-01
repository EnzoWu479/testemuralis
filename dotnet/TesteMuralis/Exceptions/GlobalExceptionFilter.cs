using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;

namespace TesteMuralis.Exceptions
{
    public class GlobalExceptionFilter : ExceptionFilterAttribute
    {
        private readonly ILogger<GlobalExceptionFilter> _logger;

        public GlobalExceptionFilter(ILogger<GlobalExceptionFilter> logger)
        {
            _logger = logger;
        }

        public override void OnException(ExceptionContext context)
        {
            var exception = context.Exception;
            

            if (exception is BadRequestException) {
                 var response = new
                {
                    StatusCode = (int)HttpStatusCode.BadRequest,
                    Message = exception.Message
                };
                context.Result = new BadRequestObjectResult(response);
            } else {
                var response = new
                {
                    StatusCode = (int)HttpStatusCode.InternalServerError,
                    Message = "An error occurred"
                };
                context.Result = new ObjectResult(response) { StatusCode = (int)HttpStatusCode.InternalServerError };
                _logger.LogError(context.Exception, "Unhandled exception occurred");
            }

            base.OnException(context);
        }
    }
}