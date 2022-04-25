package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Employee {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2)
    private String name;

    @Size(min = 2)
    private String role;

    @Column(unique = true)
    private String email;

    @DecimalMin("10.0")
    private double salary;

    @ManyToOne
    private Department department;

    public Employee() {
    }

    public Employee(String name, String role, String email, double salary) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // link to department
}
