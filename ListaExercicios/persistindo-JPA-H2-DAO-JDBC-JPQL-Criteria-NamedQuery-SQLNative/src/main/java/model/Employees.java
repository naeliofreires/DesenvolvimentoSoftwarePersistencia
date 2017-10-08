package model;

import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"cpf","matriculation"})})
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String cpf;
    @Column
    private String matriculation;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phone;

    @OneToMany(mappedBy = "empl", targetEntity = Dependents.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dependents> dependents;

    public Employees() { }

    public Employees(Integer id) { this.id = id; }

    public Employees(String cpf, String matriculation, String name, String email, String phone) {
        this.cpf = cpf;
        this.matriculation = matriculation;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatriculation() {
        return matriculation;
    }

    public void setMatriculation(String matriculation) {
        this.matriculation = matriculation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
