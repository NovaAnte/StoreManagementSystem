package se.iths.rest;

import se.iths.entity.Store;
import se.iths.service.StoreService;
import se.iths.utils.JsonFormatter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("store")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoreRest {

    StoreService storeService;

    @Inject
    public StoreRest(StoreService storeService){
        this.storeService = storeService;
    }

    @Path("")
    @POST
    public Response createStore(Store store) {
        try {
            storeService.addStore(store);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity(new JsonFormatter
                    (Response.Status.CONFLICT.getStatusCode(), "Store could not be created")).build());
        }
        return Response.ok(store).build();
    }

    @Path("{id}")
    @GET
    public Response getStoreById(@PathParam("id") Long id) {
        notFoundError(id);
        Store store = storeService.getStoreById(id);
        return Response.ok(store).build();
    }

    @Path("")
    @GET
    public Response getAllStores(){
        List<Store> foundStores;
        if(storeService.getAllStores().isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity(new JsonFormatter(204, "There are no stores added yet.")).build();
        }
        return Response.ok(storeService.getAllStores()).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateStore(@PathParam("id") Long id, Store store){
        try {
            notFoundError(id);
            store = storeService.updateStore(id, store);
        } catch (Exception e){
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(new JsonFormatter(Response.Status.BAD_REQUEST.getStatusCode(), "Failed to update store.")).build());
        }
        return Response.ok(store).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStore(@PathParam("id") Long id){
        try{
            storeService.deleteStore(id);
        } catch (Exception e){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "No customer with ID " + id + " was found.")).build());
        }
        return Response.ok(new JsonFormatter(Response.Status.OK.getStatusCode(), "Deleted customer with ID "  + id)).build();
    }

    public void notFoundError(Long id) {

        if (storeService.getStoreById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "There is no store with the id: " + id)).build());
        }
    }
}
