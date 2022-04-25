package se.iths.service;

import se.iths.entity.Department;

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

    public Department findDepartmentById(Long id) {
       Department department = entityManager.find(Department.class, id);
        return department;
    }

    public List<Department> getAllDepartments() {
       return entityManager.createQuery("SELECT d from Department d", Department.class).getResultList();
    }

    public Department updateDepartment(Long id, String departmentName) {
       Department department = entityManager.find(Department.class, id);
       department.setDepartmentName(departmentName);
        return department;
}

    public void deleteDepartment(Long id) {
        entityManager.remove(entityManager.find(Department.class, id));
    }
}