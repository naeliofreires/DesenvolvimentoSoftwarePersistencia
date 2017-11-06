package app;

import java.util.HashMap;
import java.util.Map;

import controller.RedisController;
import modelo.Artigo;

public class App {

	public static void main(String[] args) {

		RedisController redisController = new RedisController();
		Map<String, String> userProperties = new HashMap<String, String>();
		
		Artigo art1 = new Artigo("artigoUm", "alguma coisa escrita aqui","nomedoarquivo.txt", "11/05/2017");
		Artigo art2 = new Artigo("artigoDois", "tem algo mo legal aqui","nomebacana.txt", "10/08/2015");
		
//		userProperties.put("name", art1.getName() );
//		userProperties.put("description", art1.getDescription());
//		userProperties.put("filename", art1.getFilename() );
//		userProperties.put("posting_date", art1.getPosting_date());
//
//		redisController.inserirKeyValueHash(art1.getName(), userProperties);
//		
//		userProperties.put("name", art2.getName() );
//		userProperties.put("description", art2.getDescription());
//		userProperties.put("filename", art2.getFilename() );
//		userProperties.put("posting_date", art2.getPosting_date());
//		// add uma tag nova
//		userProperties.put("tag-nova", "isso e uma tag nova lesk");
//		
//		redisController.inserirKeyValueHash(art2.getName(), userProperties);

//		redisController.getAll("art:" + art1.getName());
//		System.out.println();
//		redisController.getAll("art:" + art2.getName());
//		System.out.println("\nall:tags");
//		redisController.getTags();
//		
//		redisController.getTagsArtigo(art1.getName());
//		redisController.getTagsArtigo(art2.getName());
		
		redisController.name_desc_all_artigos();
		
		

	}
}
