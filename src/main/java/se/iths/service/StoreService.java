package se.iths.service;

import se.iths.entity.Department;
import se.iths.entity.Store;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import java.util.List;

@Transactional
public class StoreService {

    @PersistenceContext
    EntityManager entityManager;

    public void addStore(Store store){entityManager.persist(store);}

    public Store getStoreById(Long id) {
        return entityManager.find(Store.class, id);
    }

    public Department getDepartmentById(Long id) {
        return entityManager.find(Department.class, id);
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


    public void linkDepartment(Long storeId, Long departmentId) {
        Store foundStore = entityManager.find(Store.class, storeId);
        Department foundDepartment = entityManager.find(Department.class, departmentId);

        foundDepartment.setStore(foundStore);
        foundStore.getDepartmentList().add(foundDepartment);
    }


    public void unlinkDepartment(Long storeId, Long departmentId) {
        Store foundStore = entityManager.find(Store.class, storeId);
        Department foundDepartment = entityManager.find(Department.class, departmentId);

        foundStore.getDepartmentList().remove(foundDepartment);
        foundDepartment.setStore(null);
    }
}
