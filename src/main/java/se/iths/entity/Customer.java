package se.iths.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    // add set/list for shoppingCart
}
