package model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@NamedQueries({
        @NamedQuery(
                name = "Dependents.LikeName",
                query = "select d from Dependents d where d.name like 'd%'")
})
public class Dependents implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String cpf;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "empl_id") // informamos o nome que terá o FK.
    private Employees empl;

    public Dependents() { }

    public Dependents(Integer id) {
        this.id = id;
    }

    public Dependents(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }

    public Dependents(Integer id, String cpf, String name) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
    }

    public Dependents(String cpf, String name, Employees empl) {
        this.cpf = cpf;
        this.name = name;
        this.empl = empl;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employees getEmpl() {
        return empl;
    }

    public void setEmpl(Employees empl) {
        this.empl = empl;
    }
}
