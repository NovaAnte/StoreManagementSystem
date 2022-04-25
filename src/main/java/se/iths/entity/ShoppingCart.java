package se.iths.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private double totalPrice;

    @OneToMany
    private List<Item> itemList;

    public ShoppingCart(){

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

    public List<Item> getItemList() {
        return itemList;
    }

}
