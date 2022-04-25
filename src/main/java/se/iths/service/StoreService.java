package se.iths.service;

import se.iths.entity.Store;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StoreService {

    @PersistenceContext
    EntityManager entityManager;

    public void addStore(Store store){entityManager.persist(store);}

    public Store getStoreById(Long id) {
        return entityManager.find(Store.class, id);
    }

    public List<Store> getAllStores(){
        return entityManager.createQuery("SELECT a from Store a", Store.class).getResultList();
    }

    public Store updateStore(Long id, Store store){
        Store foundStore = entityManager.find(Store.class, id);
        foundStore.setStoreName(store.getStoreName());
        return foundStore;
    }

    public void deleteStore(Long id){
        Store foundStore = entityManager.find(Store.class, id);
        entityManager.remove(foundStore);
    }
}
