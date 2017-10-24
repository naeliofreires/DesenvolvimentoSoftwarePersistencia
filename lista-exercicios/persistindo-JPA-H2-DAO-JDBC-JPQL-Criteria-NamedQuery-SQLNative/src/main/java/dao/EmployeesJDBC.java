package dao;

import interfaces.iEmployees;
import model.Employees;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesJDBC implements iEmployees {

    public EmployeesJDBC() { }

    public void save(Employees entity) {

        Connection con = null;

        try {

            con = ConnectionFactory.getConnection();

            String insert_sql =
                    "insert into employees (cpf, matriculation, name, email, phone) values (?, ?, ?, ?, ?)";
            String update_sql =
                    "update employees set cpf = ?, matriculation = ?, name = ?, email = ?, phone = ? where id = ?";

            PreparedStatement stmt;
            if (entity.getId() == null) {
                stmt = con.prepareStatement(insert_sql);
            } else {
                stmt = con.prepareStatement(update_sql);
                stmt.setInt(6, entity.getId());
            }

            stmt.setString(1, entity.getCpf());
            stmt.setString(2, entity.getMatriculation());
            stmt.setString(3, entity.getName());
            stmt.setString(4, entity.getEmail());
            stmt.setString(5, entity.getPhone());

            stmt.executeUpdate();

            ConnectionFactory.statementClose(stmt);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (con != null)
                ConnectionFactory.connectionClose(con);
        }
    }

    public void delete(int id) {
        
        Connection con = null;

        try{

            con = ConnectionFactory.getConnection();
            
            String sql = "delete from employees where id = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();

            ConnectionFactory.statementClose(pst);

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if (con != null)
                ConnectionFactory.connectionClose(con);
        }
    }

    public Employees find(int id) {

        Connection con = null;
        Employees empl = null;

        try{

            con = ConnectionFactory.getConnection();
            
            String sql = "select * from employees where id = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();

            if (rs.next()) 
                empl = map(rs);

            ConnectionFactory.resultSetClose(rs);
            ConnectionFactory.statementClose(pst);

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if (con != null)
                ConnectionFactory.connectionClose(con);
        }

        return empl;
    }

    public List<Employees> find() {

        Connection con = null;
        List<Employees> result = null;

        try {
            
            con = ConnectionFactory.getConnection();
            String sql = "select * from employees";

            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            result = new ArrayList<Employees>();
            
            while (rs.next()) {
                Employees cl = map(rs);
                result.add(cl);
            }

            ConnectionFactory.resultSetClose(rs);
            ConnectionFactory.statementClose(stmt);

        }catch(SQLException e){
            e.printStackTrace();

        }finally {
            if (con != null)
                ConnectionFactory.connectionClose(con);
        }
        return result;
    }

    public Employees findByCpf(String cpf) {
        
        Connection con = null;
        Employees cl = null;
        
        try {
            
            con = ConnectionFactory.getConnection();
            String sql = "select * from Employees where cpf = ?";

            PreparedStatement pst;
            pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next())
                cl = map(rs);

            ConnectionFactory.resultSetClose(rs);
            ConnectionFactory.statementClose(pst);

        }catch(SQLException e){
            e.printStackTrace();

        }finally {
            if (con != null)
                ConnectionFactory.connectionClose(con);
        }
        return cl;
    }

    public List<Employees> findByNome(String str) {

        Connection con = null;
        List<Employees> result = null;

        try {
            con = ConnectionFactory.getConnection();

            String sql = "select * from employees where upper(name) like ?";

            PreparedStatement pst;
            pst = con.prepareStatement(sql);
            pst.setString(1, "%" + str.toUpperCase() + "%");
            
            ResultSet rs = pst.executeQuery();
            result = new ArrayList<Employees>();
            
            while (rs.next()) {
                Employees cl = map(rs);
                result.add(cl);
            }

            ConnectionFactory.resultSetClose(rs);
            ConnectionFactory.statementClose(pst);

        }catch(SQLException e){
            e.printStackTrace();

        }finally {
            if (con != null)
                ConnectionFactory.connectionClose(con);
        }
        return result;
    }

    private Employees map(ResultSet rs) throws SQLException {
        Employees cl = new Employees();
        cl.setId(rs.getInt("id"));
        cl.setCpf(rs.getString("cpf"));
        cl.setMatriculation(rs.getString("matriculation"));
        cl.setName(rs.getString("name"));
        cl.setEmail(rs.getString("email"));
        cl.setPhone(rs.getString("phone"));
        return cl;
    }
}
