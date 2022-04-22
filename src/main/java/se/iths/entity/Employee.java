package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Employee {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min = 2)
    private String name;

    @NotEmpty
    @Size(min = 2)
    private String role;

    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    private double Salary;

    // link to department
}
