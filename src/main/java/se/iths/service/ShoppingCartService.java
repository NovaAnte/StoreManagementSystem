package se.iths.service;

import se.iths.entity.Item;
import se.iths.entity.ShoppingCart;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class ShoppingCartService {

    @PersistenceContext
    EntityManager entityManager;

    public void createShoppingCart(ShoppingCart shoppingCart) {
        entityManager.persist(shoppingCart);
    }

    public ShoppingCart getShoppingCartById(Long id) {
        return entityManager.find(ShoppingCart.class, id);
    }

    public Item getItemById(Long id) {
        return entityManager.find(Item.class, id);
    }

    public void linkItemToShoppingCart(Long cartId, Long itemId) {
        ShoppingCart foundCart = entityManager.find(ShoppingCart.class, cartId);
        Item foundItem = entityManager.find(Item.class, itemId);

        entityManager.persist(foundCart.getItemList().add(foundItem));
    }

    public void unlinkItemFromShoppingCart(Long cartId, Long itemId) {
        ShoppingCart foundCart = entityManager.find(ShoppingCart.class, cartId);
        Item foundItem = entityManager.find(Item.class, itemId);

        entityManager.persist(foundCart.getItemList().remove(foundItem));
    }
}
