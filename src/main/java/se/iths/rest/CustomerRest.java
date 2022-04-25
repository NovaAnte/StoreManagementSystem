package se.iths.rest;

import se.iths.entity.Customer;
import se.iths.service.CustomerService;
import se.iths.utils.JsonFormatter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @Path("{id}")
    @GET
    public Response findCustomer(@PathParam("id") Long id) {
        Customer foundCustomer;
        try {
            foundCustomer = customerService.findCustomer(id);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "Could not find customer with that ID.")).build());
        }
        return Response.ok(foundCustomer).build();
    }

    @Path("findall")
    @GET
    public Response findAllCustomers(){ // får no body istället för exception i insomnia
        List<Customer> foundCustomers;
        try {
            foundCustomers = customerService.findAllCustomers();
        } catch (Exception e){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "Could not find any customers.")).build());
        }
        if (foundCustomers.isEmpty()){
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity(new JsonFormatter(Response.Status.NO_CONTENT.getStatusCode(), "Could not find any customers.")).build());
        }
        return Response.ok(foundCustomers).build();
    }

    @Path("update/{id}")
    @PATCH
    public Response updateCustomer(@PathParam("id") Long id, Customer customer){
        try {
            notFoundError(id);
            customer = customerService.updateCustomer(id, customer);
        } catch (Exception e){
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(new JsonFormatter(Response.Status.BAD_REQUEST.getStatusCode(), "Failed to update customer.")).build());
        }
        return Response.ok(customer).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteCustomer(@PathParam("id") Long id){
        try{
            customerService.deleteCustomer(id);
        } catch (Exception e){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "No customer with that ID.")).build());
        }
        return Response.ok(new JsonFormatter(Response.Status.OK.getStatusCode(), "Deleted customer with ID "  + id)).build();
    }

    public void notFoundError(Long id) {

        if (customerService.findCustomer(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "There is no student with the id: " + id)).build());
        }
    }
}
