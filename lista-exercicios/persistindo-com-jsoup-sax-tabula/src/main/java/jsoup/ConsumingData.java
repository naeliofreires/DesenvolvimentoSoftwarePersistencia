package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ConsumingData {

    public CheckingFile checkingFile;

    public ConsumingData(){
        this.checkingFile = new CheckingFile();
    }

    public void MercadoLivre(String choice){
       try{
           Document doc = Jsoup.connect("https://lista.mercadolivre.com.br/" + choice).get();

           Elements elements_tittles = doc.select(".main-title");
           ArrayList<String> titles = (ArrayList<String>) elements_tittles.eachText();

           Elements elements_price = doc.select(".item__price");
           ArrayList<String> price = (ArrayList<String>) elements_price.eachText();

           // verifying that the file exists, if not, it is created
           if(!checkingFile.fileExists())
               checkingFile.createFile();

           checkingFile.getProperties().load(new FileInputStream(System.getProperty("user.home") + "/config.properties"));

           PrintStream printStream = new PrintStream(checkingFile.getProperties().getProperty("file-path"));

           for(int i =0; i  < titles.size(); i++)
               printStream.println(titles.get(i) + "," + price.get(i));
           printStream.close();


       }catch (Exception ex){
           ex.printStackTrace();
       }
    }
}
