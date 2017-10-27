package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controlls.ManagingDatabase;
import models.Editora;
import models.Livro;
import org.bson.Document;

public class main {
    public static void main(String[] args) throws JsonProcessingException {

        Livro livro = new Livro();
        Editora editora = new Editora();
        ManagingDatabase managingDatabase = new ManagingDatabase();

        ObjectMapper mapper = new ObjectMapper();

        for(Livro l : livro.criarLivro()){
            Document document = Document.parse(mapper.writeValueAsString(l));
            managingDatabase.InserirDocumento("livros", document);
        }

        for (Editora e : editora.criarEditoras()){
            Document document = Document.parse(mapper.writeValueAsString(e));
            managingDatabase.InserirDocumento("editoras", document);
        }
    }
}
