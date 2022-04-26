package se.iths.service;

import se.iths.entity.Department;
import se.iths.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class DepartmentService {

    @PersistenceContext
    EntityManager entityManager;

    public void addDepartment(Department department) {
        entityManager.persist(department);
    }

    public Department getDepartmentById(Long id) {
        return entityManager.find(Department.class, id);
    }

    public List<Department> getAllDepartments() {
        return entityManager.createQuery("SELECT d from Department d", Department.class).getResultList();
    }

    public Department updateDepartment(Long id, String departmentName) {
        Department department = getDepartmentById(id);
        department.setDepartmentName(departmentName);
        return department;
    }

    public void deleteDepartment(Long id) {
        entityManager.remove(entityManager.find(Department.class, id));
    }

    public Employee linkEmployeeToDepartment(Long id, String email) {

        Department department = getDepartmentById(id);
        Employee employee = (Employee) entityManager.createQuery("Select e from Employee e where e.email = :email")
                .setParameter("email", email).getSingleResult();
        department.addEmployee(employee);
        return employee;
    }

    public Employee unlinkEmployeeToDepartment(Long id, String email) {
        Department department = getDepartmentById(id);
        Employee employee = (Employee) entityManager.createQuery("Select e from Employee e where e.email = :email")
                .setParameter("email", email).getSingleResult();
        department.removeEmployee(employee);
        return employee;
    }
}