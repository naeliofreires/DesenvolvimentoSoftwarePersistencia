package dao;

import interfaces.EmployeesDAO;
import model.Employees;

import java.util.List;

public class EmployeesJPADAO extends JPAGeneric<Employees> implements EmployeesDAO {
    public EmployeesJPADAO(Class<Employees> persistentClass) {
        super(persistentClass);
    }

}
