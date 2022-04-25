package se.iths.rest;

import se.iths.entity.Item;
import se.iths.service.ItemService;
import se.iths.utils.JsonFormatter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("item")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemRest {

    ItemService itemService;

    @Inject
    public ItemRest(ItemService itemService){ this.itemService = itemService; }

    @Path("")
    @POST
    public Response createItem(Item item) {
        try {
            itemService.addItem(item);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity(new JsonFormatter
                    (Response.Status.CONFLICT.getStatusCode(), "Item could not be created")).build());
        }
        return Response.ok(item).build();
    }

    @Path("{id}")
    @GET
    public Response getItemById(@PathParam("id") Long id) {
        notFoundError(id);
        Item item = itemService.getItemById(id);
        return Response.ok(item).build();
    }

    @Path("")
    @GET
    public Response getAllItems(){
        List<Item> foundItems = itemService.getAllItems();
        if(foundItems.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity(new JsonFormatter(204, "There are no items added yet.")).build();
        }
        return Response.ok(foundItems).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateItem(@PathParam("id") Long id, Item item){
        try {
            notFoundError(id);
            item = itemService.updateItem(id, item);
        } catch (Exception e){
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(new JsonFormatter(Response.Status.BAD_REQUEST.getStatusCode(), "Failed to update item.")).build());
        }
        return Response.ok(item).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteItem(@PathParam("id") Long id){
        try{
            itemService.deleteItem(id);
        } catch (Exception e){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "No item with ID " + id + " was found.")).build());
        }
        return Response.ok(new JsonFormatter(Response.Status.OK.getStatusCode(), "Deleted item with ID "  + id)).build();
    }

    public void notFoundError(Long id) {

        if (itemService.getItemById(id) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "There is no item with the id: " + id)).build());
        }
    }
}
