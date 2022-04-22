package se.iths.rest;

import se.iths.entity.Customer;
import se.iths.service.CustomerService;
import se.iths.utils.JsonFormatter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerRest {

    CustomerService customerService;

    @Inject
    public CustomerRest (CustomerService customerService){
        this.customerService = customerService;
    }

    @Path("")
    @POST
    public Response createCustomer(Customer customer){
        try {
            customerService.addCustomer(customer);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity(new JsonFormatter(Response.Status.CONFLICT.getStatusCode(), "Customer could not be created.")).build());
        }
        return Response.ok(customer).build();
    }
}
