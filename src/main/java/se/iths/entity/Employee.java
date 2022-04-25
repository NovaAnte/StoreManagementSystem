package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
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
    private String firstName;
    @Size(min = 2)
    private String lastName;

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

    public Employee(String firstName, String lastName, String role, String email, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.salary = salary;
    }

    public Long getId() {
        return id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonbTransient //TODO: Detta får vi kolla på, man vill ju kunna få fram vilken department man jobbar på.
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    // link to department
}
