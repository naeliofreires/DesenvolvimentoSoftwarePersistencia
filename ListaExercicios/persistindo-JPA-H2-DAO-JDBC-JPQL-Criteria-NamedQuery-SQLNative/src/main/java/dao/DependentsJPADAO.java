package dao;

import interfaces.DependentsDAO;
import model.Dependents;

public class DependentsJPADAO extends JPAGeneric<Dependents> implements DependentsDAO{
    public DependentsJPADAO(Class<Dependents> persistentClass) {
        super(persistentClass);
    }
}
