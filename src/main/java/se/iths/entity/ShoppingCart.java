package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private double totalPrice;

    @ManyToMany
    Set<Item> items = new HashSet<>();

    @OneToOne
    private Customer customer;


    public ShoppingCart(){

    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    @JsonbTransient
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Item> getItems() {
        return items;
    }
}
