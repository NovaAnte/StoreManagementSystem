package se.iths.utils;

import se.iths.entity.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleData {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData(){
        Customer customer = new Customer("Lars", "Karlsson");
        Customer customer2 = new Customer("Benny", "Bajsson");

        Department department = new Department("Frukt & Grönt");
        Department department2 = new Department("Chark");

        Employee employee = new Employee("Peter", "Stormare", "Styckare", "sexigskådis@gmail.com", 20000.00);
        Employee employee2 = new Employee("Kenny", "Bräck", "Äppelpolerare",  "dåligracerförare@gmail.com", 30000.00);

        Item item = new Item("Royal Gala", 10);
        Item item2 = new Item("Cantaloupe", 20);
        Item item3 = new Item("Coppa di Parma", 60);
        Item item4 = new Item("Serrano", 50);

        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCart shoppingCart2 = new ShoppingCart();

        Store store = new Store("ITHS Maxi Lindhagen");

     //   entityManager.persist(customer);
     //   entityManager.persist(customer2);
        entityManager.persist(department);
        entityManager.persist(department2);
        entityManager.persist(employee);
        entityManager.persist(employee2);
        entityManager.persist(item);
        entityManager.persist(item2);
        entityManager.persist(item3);
        entityManager.persist(item4);
      //  entityManager.persist(shoppingCart);
       // entityManager.persist(shoppingCart2);
        entityManager.persist(store);
    }

}
