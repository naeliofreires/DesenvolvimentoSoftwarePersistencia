import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ConsumingData {

    public void MercadoLivre(String choice){
       try{
           Document doc = Jsoup.connect("https://lista.mercadolivre.com.br/" + choice).get();

           Elements elements_tittles = doc.select(".main-title");
           ArrayList<String> titles = (ArrayList<String>) elements_tittles.eachText();

           Elements elements_price = doc.select(".item__price");
           ArrayList<String> price = (ArrayList<String>) elements_price.eachText();

//           for (int i = 0; i < titles.size(); i++ ) {
//               System.out.println(titles.get(i) + " - " + price.get(i));
//           }
       }catch (Exception ex){
           ex.printStackTrace();
       }
    }
}
