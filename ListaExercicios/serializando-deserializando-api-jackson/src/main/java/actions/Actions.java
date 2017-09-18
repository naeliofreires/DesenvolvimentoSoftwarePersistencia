package actions;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Dados;
import model.Deputado;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Actions {

    private static ObjectMapper mapper;

    private static final String HOME_USER = System.getProperty("user.home");
    private static final String myURL = "https://dadosabertos.camara.leg.br/api/v2/deputados?ordem=ASC&ordenarPor=nome";

    public Actions() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    /**Crie uma classe Java para baixar através do Java/Jackson
     * o JSON acima descrito a partir da URL original e não do
     * arquivo salvo na questão anterior. Depois disso, serialize
     * o JSON para objetos Java.**/

    //VERSÃO 01
    public void JaksonJson(){
        try {
            Dados data = mapper.readValue(new URL(myURL), Dados.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    //VERSÃO 02
    public ArrayList<Deputado> SerializarJSONForObjectJava() throws IOException {
        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(new URL(myURL));
            while (parser.nextToken() != JsonToken.START_ARRAY) // avança o stream até chegar no array
                parser.nextToken();
            List<Deputado> deputadosList = mapper.readValue(parser, new TypeReference<List<Deputado>>() {});
            return (ArrayList<Deputado>) deputadosList;
        }catch (Exception ex){ ex.printStackTrace(); }
        return null;
    }

    /**Crie uma classe Java para serializar os objetos criados na questão 2 contendo os dados do JSON.
     * Serialize e salve em um arquivo usando a serialização padrão do Java.**/

    public void SerializarJava(){
        try {
            ArrayList<Deputado> deputados = SerializarJSONForObjectJava();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(HOME_USER + "/deputados.ser"));
            out.writeObject(deputados);
            out.close();
            System.out.println("Serializado . . .");
        }
        catch (JsonParseException ex) { ex.printStackTrace(); }
        catch (JsonMappingException ex) { ex.printStackTrace(); }
        catch (IOException ex){ ex.printStackTrace();}
    }

    /**Serialize e salve em um arquivo usando a serialização formatada JSON do Jackson.
     * Ou seja: o arquivo deve ser salvo formatado.
     * Dica: Use o mapper.defaultPrettyPrintingWriter().writeValueAsString(json) do Jackson.**/

    public void SerializarJackson(){
        try {
            ArrayList<Deputado> deputados = SerializarJSONForObjectJava();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(HOME_USER + "/deputados.json_pretty"));
            out.writeObject(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(deputados));
            out.close();
        }
        catch (JsonParseException e) { e.printStackTrace(); }
        catch (JsonMappingException e) { e.printStackTrace(); }
        catch (IOException ex){ex.printStackTrace();}

    }

    /**Crie uma classe Java para deserializar e exibir os dados do primeiro arquivo criado na questão 3.**/
    public void deserializarJavaSer() throws FileNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HOME_USER + "/deputados.ser"));
            ArrayList<Deputado> deputados = (ArrayList<Deputado>) ois.readObject();
            System.out.println(deputados);
        }
        catch (Exception ex){ex.printStackTrace(); }
    }

}
