package models;

import java.util.ArrayList;

public class Livro {

    private Integer isbn;
    private String  titulo;
    private Integer ano_publicação;
    private Integer qtd_estoque;
    private Double  valor;
    private Integer id_editora;

    public Livro() {}

    public Livro(Integer isbn, String titulo, Integer ano_publicação, Integer qtd_estoque, Double valor, Integer id_editora) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano_publicação = ano_publicação;
        this.qtd_estoque = qtd_estoque;
        this.valor = valor;
        this.id_editora = id_editora;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno_publicação() {
        return ano_publicação;
    }

    public void setAno_publicação(Integer ano_publicação) {
        this.ano_publicação = ano_publicação;
    }

    public Integer getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(Integer qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getId_editora() {
        return id_editora;
    }

    public void setId_editora(Integer id_editora) {
        this.id_editora = id_editora;
    }

    public ArrayList<Livro> criarLivro(){
        ArrayList<Livro> livros = new ArrayList<Livro>();

        livros.add(new Livro(213,"Banco de Dados", 2011, 2, 50.00, 4));
        livros.add(new Livro(425,"Sistemas Operacionais", 2010, 3, 80.00, 3));
        livros.add(new Livro(732,"Lógica de Programação", 2009, 1, 60.00, 2));
        livros.add(new Livro(441,"Programação Orientada a Objetos", 2012, 1, 70.00, 1));
        livros.add(new Livro(659,"Java para Nerds", 2010, 3, 90.00, 4));
        livros.add(new Livro(863,"Engenharia de Software", 2010, 2, 40.00, 2));
        livros.add(new Livro(376,"Redes de Computadores", 2008, 1, 100.00, 3));

        return livros;
    }
}
