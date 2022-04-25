package se.iths.service;

import se.iths.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class EmployeeService {
    @PersistenceContext
    EntityManager entityManager;


    public void addEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    public Employee getEmployeeById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    public List<Employee> getAllEmployees() {
        return entityManager.createQuery("Select e from Employee e").getResultList();
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee updateEmp = getEmployeeById(id);

        if(employee.getRole() != null) {
            updateEmp.setRole(employee.getRole());
        }

        if(employee.getSalary() != 0) {
            updateEmp.setSalary(employee.getSalary());
        }

        if (employee.getEmail() != null) {
            updateEmp.setEmail(employee.getEmail());
        }

        if (employee.getFirstName() != null) {
            updateEmp.setFirstName(employee.getFirstName());
        }

        if (employee.getFirstName() != null) {
            updateEmp.setLastName(employee.getLastName());
        }
        return employee;
    }
}
