package com.izdebski.dao.impl;

import com.izdebski.dao.EmployeeDAO;
import com.izdebski.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void createEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    @Transactional
    @Override
    public Employee getEmployeeById(int employeeId) {
        return  entityManager.find(Employee.class, employeeId);
    }

    @Transactional
    @Override
    public void deleteEmployeeById(int employeeId) {
       Employee employee = entityManager.find(Employee.class, employeeId);
       if(employee != null){
           entityManager.remove(employee);
       }
    }

    @Transactional
    @Override
    public void updateEmployeeEmailById(String newEmail, int employeeId) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        if (employee != null) {
            employee.setEmail(newEmail);
            entityManager.merge(employee);
        }
    }

    @Transactional
    @Override
        public List<Employee> getAllEmployeesDetails () {
        Query query = entityManager.createQuery("FROM Employee");
        query.getResultList();

        List<Employee>empList = query.getResultList();
            return empList;
    }
}