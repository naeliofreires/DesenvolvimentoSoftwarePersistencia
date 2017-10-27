package models;

import java.util.ArrayList;

public class Editora {

    private Integer id;
    private String nome;

    public Editora() {}

    public Editora(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Editora> criarEditoras(){

        ArrayList<Editora> editoras = new ArrayList<Editora>();

        Editora ed1 = new Editora(1,"Ática");
        editoras.add(ed1);
        Editora ed2 = new Editora(2,"FTD");
        editoras.add(ed2);
        Editora ed3 = new Editora(3,"Melhoramentos");
        editoras.add(ed3);
        Editora ed4 = new Editora(4,"Novatec");
        editoras.add(ed4);
        Editora ed5 = new Editora(5,"Bookman");
        editoras.add(ed5);

        return editoras;
    }
}
