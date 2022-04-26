package se.iths.rest;


import se.iths.entity.Department;
import se.iths.entity.Employee;
import se.iths.service.DepartmentService;
import se.iths.utils.JsonFormatter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("department")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentRest {
    DepartmentService departmentService;

    @Inject
    public DepartmentRest(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Path("")
    @POST
    public Response createDepartment(Department department) {
        try {
            departmentService.addDepartment(department);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity(new JsonFormatter
                    (Response.Status.CONFLICT.getStatusCode(), "Could not add new department")).build());
        }
        return Response.ok(department).build();
    }

    @Path("{id}")
    @GET
    public Response getDepartmentById(@PathParam("id") Long id) {
        notFoundError(id);
        Department department = departmentService.getDepartmentById(id);
        return Response.ok(department).build();
    }

    @Path("")
    @GET
    public Response getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        if(departments.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity(new JsonFormatter(204, "There are no departments added yet.")).build();
        }
        return Response.ok(departments).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateDepartmentName(@PathParam("id") Long id, Department department ) {
        notFoundError(id);
        Department updateDepartment = departmentService.updateDepartment(id, department.getDepartmentName());
        return Response.ok(updateDepartment).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteDepartment(@PathParam("id") Long id) {
        notFoundError(id);
        departmentService.deleteDepartment(id);
        return Response.ok().entity(new JsonFormatter(200, "Successfully deleted department with ID: " + id)).build();
    }

    @Path("linkemployee/{id}")
    @PATCH
    public Response linkEmployeeToDepartment(@PathParam("id")Long id, @QueryParam("email")String email) {
        notFoundError(id);
        Employee connectedEmployee = departmentService.linkEmployeeToDepartment(id, email);
        if(connectedEmployee.getEmail() == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter
                    (Response.Status.NOT_FOUND.getStatusCode(), "Could not find an employee with the email: " + email)).build());
        }
        return Response.ok(connectedEmployee).build();
    }

    @Path("unlinkemployee/{id}")
    @PATCH
    public Response unlinkEmployeeToDepartment(@PathParam("id")Long id, @QueryParam("email")String email) {
        notFoundError(id);
        Employee connectedEmployee = departmentService.unlinkEmployeeToDepartment(id, email);
        if(connectedEmployee.getEmail() == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter
                    (Response.Status.NOT_FOUND.getStatusCode(), "Could not find an employee with the email: " + email)).build());
        }
        return Response.ok(connectedEmployee).build();
    }


    private void notFoundError(Long id) {
        if (departmentService.getDepartmentById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(404, "ID: " + id + " not found")).build());
        }
    }
}
