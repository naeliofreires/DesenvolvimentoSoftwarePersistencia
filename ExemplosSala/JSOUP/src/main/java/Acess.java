import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Acess {

    public static void main(String[] args) throws IOException {

//        Document doc = Jsoup.connect("https://www.americanas.com.br/produto/8462174/battlefield-4-xbox-one?pfm_carac=Console%20XBOX%20One&pfm_index=6&pfm_page=category&pfm_pos=grid&pfm_type=vit_product_grid").get();

//        String title = doc.title();
//        System.out.println(title);

//        Element price = doc.select(".sales-price").first();
//        System.out.println(price.text());
//
//        Element id = doc.select(".product-id").first();
//        System.out.println(id.text());

        Document doc = Jsoup.connect("https://ecommerce.grupoguanabara.net.br:8080/VendaWebGuanabara/consulta?buca_ori_=%22Quixada%22&buca_des_=%22Fortaleza%22&dataInicial=%2206/09/2017%22").get();
        String title = doc.title();
        System.out.println(title);

        Elements table = doc.select("#carrega-ida");
        System.out.println(table.text());
    }
}
