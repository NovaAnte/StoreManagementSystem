package se.iths.rest;

import se.iths.entity.Employee;
import se.iths.service.EmployeeService;
import se.iths.utils.JsonFormatter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeRest {
    EmployeeService employeeService;

    @Inject
    EmployeeRest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Path("")
    @POST
    public Response createEmployee(Employee employee) {
        try {
            employeeService.addEmployee(employee);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT)
                    .entity(new JsonFormatter(Response.Status.CONFLICT.getStatusCode(), "Could not add new employee")).build());
        }
        return Response.ok(employee).build();
    }

    @Path("{id}")
    @GET
    public Response getEmployeeById(@PathParam("id") Long id) {
        notFoundError(id);
       return Response.ok(employeeService.getEmployeeById(id)).build();
    }

    @Path("")
    @GET
    public Response getAllEmployees() {
        if (employeeService.getAllEmployees().isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity(new JsonFormatter(Response.Status.NO_CONTENT.getStatusCode(), "There are no employees added yet")).build();
        }
        return Response.ok(employeeService.getAllEmployees()).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateEmployee(@PathParam("id")Long id, Employee employee) {
        employeeService.updateEmployee(id, employee);
        return Response.ok(employee).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteDepartment(@PathParam("id") Long id) {
        notFoundError(id);
        employeeService.deleteDepartment(id);
        return Response.ok().entity(new JsonFormatter(200, "Successfully deleted employee with ID: " + id)).build();
    }

    private void notFoundError(Long id) {
        if (employeeService.getEmployeeById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(404, "ID: " + id + " not found")).build());
        }
    }
}
