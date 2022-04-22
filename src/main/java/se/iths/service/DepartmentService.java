package se.iths.service;

import se.iths.entity.Department;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
}