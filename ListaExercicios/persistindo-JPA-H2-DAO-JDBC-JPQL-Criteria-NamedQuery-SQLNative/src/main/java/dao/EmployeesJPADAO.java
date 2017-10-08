package dao;

import interfaces.EmployeesDAO;
import model.Employees;

public class EmployeesJPADAO extends JPAGeneric<Employees> implements EmployeesDAO {
    public EmployeesJPADAO(Class<Employees> persistentClass) {
        super(persistentClass);
    }
}
