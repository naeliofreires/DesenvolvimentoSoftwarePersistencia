package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import controlls.ManagingDatabase;
import models.Editora;
import models.Livro;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws JsonProcessingException {

        Livro livro = new Livro();
        Editora editora = new Editora();
        ManagingDatabase managingDatabase = new ManagingDatabase();

//        managingDatabase.InserirDocumento("livros", new ArrayList<Object>(livro.criarLivro()));
//        managingDatabase.InserirDocumento("editoras", new ArrayList<Object>(editora.criarEditoras()));

        managingDatabase.showCollection("livros");
        managingDatabase.showCollection("editoras");
    }
}
