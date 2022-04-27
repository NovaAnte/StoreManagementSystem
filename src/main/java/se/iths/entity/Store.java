package se.iths.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String storeName;


    @OneToMany
    private List<Department> departmentList;


    public Store() {
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public Store(String storeName) {
        this.storeName = storeName;
    }

    public Long getId() {
        return id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
