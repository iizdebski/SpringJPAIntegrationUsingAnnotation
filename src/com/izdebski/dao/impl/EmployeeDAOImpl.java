package com.izdebski.dao.impl;

import com.izdebski.dao.EmployeeDAO;
import com.izdebski.model.Employee;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void createEmployee(Employee employee) {
        hibernateTemplate.save(employee);
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Employee employee = hibernateTemplate.get(Employee.class, employeeId);
        return employee;
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        hibernateTemplate.delete(employee);
    }

    @Override
    public void updateEmployeeEmailById(String newEmail, int employeeId) {
        Employee employee = hibernateTemplate.get(Employee.class, employeeId);
        employee.setEmail(newEmail);
        hibernateTemplate.update(employee);
    }

    @Override
    public List<Employee> getAllEmployeesDetails(){

        DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
        List<Employee> EmpList = (List<Employee>) hibernateTemplate.findByCriteria(criteria);
        return EmpList;
    }

}