package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String departmentName;

    @OneToMany
    private List<Employee> employeeList;

    @ManyToOne
    private Store store;


    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    @JsonbTransient
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        employeeList.remove(employee);
    }

    // link to items, employee
}
