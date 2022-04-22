package se.iths.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;


}
