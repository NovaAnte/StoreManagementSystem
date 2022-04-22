package se.iths.service;

import se.iths.entity.Customer;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class CustomerService {

    @PersistenceContext
    EntityManager entityManager;

    public void addCustomer(Customer customer){
        entityManager.persist(customer);
    }

    public Customer findCustomer(Long id) {
        Customer foundCustomer = entityManager.find(Customer.class, id);
        return foundCustomer;
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Customer foundCustomer = entityManager.find(Customer.class, id);
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        return foundCustomer;
    }
}
