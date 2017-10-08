package interfaces;

import model.Employees;

import java.util.List;

public interface iEmployees {
    public void save(Employees entity);
    public void delete(int id);
    public Employees find(int id);
    public List<Employees> find();
    public Employees findByCpf(String cpf);
    public List<Employees> findByNome(String str);
}
