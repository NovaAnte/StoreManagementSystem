package se.iths.service;

import se.iths.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ItemService {

    @PersistenceContext
    EntityManager entityManager;

    public void addItem(Item item){entityManager.persist(item);}

    public Item getItemById(Long id){
        return entityManager.find(Item.class, id);
    }

    public List<Item> getAllItems(){
        return entityManager.createQuery("SELECT a from Item a", Item.class).getResultList();
    }

    public Item updateItem(Long id, Item item){
        Item foundItem = entityManager.find(Item.class,id);
        foundItem.setName(item.getName());
        foundItem.setPrice(item.getPrice());
        return foundItem;
    }

    public void deleteItem(Long id){
        Item foundItem = entityManager.find(Item.class, id);
        entityManager.remove(foundItem);
    }
}
