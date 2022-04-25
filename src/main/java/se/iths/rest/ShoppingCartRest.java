package se.iths.rest;

import se.iths.entity.Customer;
import se.iths.entity.Item;
import se.iths.entity.ShoppingCart;
import se.iths.service.ShoppingCartService;
import se.iths.utils.JsonFormatter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("shoppingcart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ShoppingCartRest {
    ShoppingCartService shoppingCartService;

    @Inject
    public ShoppingCartRest(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Path("")
    @POST
    public Response createShoppingCart(ShoppingCart shoppingCart) {
        try {
            shoppingCartService.createShoppingCart(shoppingCart);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity(new JsonFormatter(Response.Status.CONFLICT.getStatusCode(), "Could not create new shopping cart.l")).build());
        }
        return Response.ok(shoppingCart).build();
    }

    @Path("{id}")
    @GET
    public Response getShoppingCartById(@PathParam("id") Long id) {
        ShoppingCart foundCart;
        try {
            foundCart = shoppingCartService.getShoppingCartById(id);
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), "Could not find a shopping cart with that ID.")).build());
        }
        return Response.ok(foundCart).build();
    }

    @Path("link")
    @PATCH
    public Response linkItemToShoppingCart(@QueryParam("cartId") Long cartId, @QueryParam("itemId") Long itemId) {
        ShoppingCart foundCart = shoppingCartService.getShoppingCartById(cartId);
        Item foundItem = shoppingCartService.getItemById(itemId);

        try {
            shoppingCartService.linkItemToShoppingCart(cartId, itemId);
        } catch (Exception e) {
            String exceptionHelper = null;
            if (foundCart == null) {
                exceptionHelper = "Shopping cart not found";
            }
            if (foundItem == null) {
                exceptionHelper = "Item not found";
            }
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), exceptionHelper)).build());
        }
        return Response.ok(new JsonFormatter(Response.Status.OK.getStatusCode(), "Item with ID " + foundItem.getId() + " added to cart with ID " + foundCart.getId())).build();
    }

    @Path("unlink")
    @PATCH
    public Response unlinkItemFromShoppingCart(@QueryParam("cartId") Long cartId, @QueryParam("itemId") Long itemId) {
        ShoppingCart foundCart = shoppingCartService.getShoppingCartById(cartId);
        Item foundItem = shoppingCartService.getItemById(itemId);

        try {
            shoppingCartService.unlinkItemFromShoppingCart(cartId, itemId);
        } catch (Exception e) {
            String exceptionHelper = null;
            if (foundCart == null) {
                exceptionHelper = "Shopping cart not found";
            }
            if (foundItem == null) {
                exceptionHelper = "Item not found";
            }
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(new JsonFormatter(Response.Status.NOT_FOUND.getStatusCode(), exceptionHelper)).build());
        }
        return Response.ok(new JsonFormatter(Response.Status.OK.getStatusCode(), "Item with ID " + foundItem.getId() + " removed from cart with ID " + foundCart.getId())).build();
    }


}
