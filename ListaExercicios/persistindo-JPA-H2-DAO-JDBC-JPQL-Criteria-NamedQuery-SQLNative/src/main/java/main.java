import dao.EmployeesJPADAO;
import dao.JPAGeneric;
import interfaces.EmployeesDAO;
import model.Employees;
import util.JPAUtil;

import java.util.List;


public class main {
    public static void main(String[] args) {

        JPAUtil.init("devPostgreSQL");

        EmployeesDAO employeesDAO = new EmployeesJPADAO(Employees.class);


    }
}
