package se.iths.rest;


import se.iths.entity.Department;
import se.iths.service.DepartmentService;
import se.iths.utils.JsonFormatter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        try{
            departmentService.addDepartment(department);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity(new JsonFormatter
                    (Response.Status.CONFLICT.getStatusCode(), "Could not add new department")).build());
        }
        return Response.ok(department).build();
    }
}
