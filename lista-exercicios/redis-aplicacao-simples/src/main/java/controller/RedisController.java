package controller;

import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisController {

	private Jedis jedis;
	
	public RedisController() {
		jedis = new Jedis("localhost");
	}
	
	// inserindo key - valor
	public void inserirKeyValue(String key, String value) {
		jedis.set(key, value);
	}
	
	// buscando valor
	public void getValue(String key) {
		System.out.println(jedis.get(key));
	}

	// inserindo um map e sua respectiva tag
	public void inserirKeyValueHash(String tag, Map<String, String> userProperties) {
		String key = "art:" + tag;
		jedis.hmset(key, userProperties);
		jedis.sadd("all:art", tag);
	}
	
	// imprindo tags e valor 
	public void getAll(String key) {
		Map<String, String> all =  jedis.hgetAll(key);
		
		for(Map.Entry<String, String> elemento : all.entrySet()) {
			System.out.println(elemento.getKey() + " " + elemento.getValue());
		}
	}
	
	// todas as tags
	public Set<String> getTags() {
		return jedis.smembers("all:art");
	}
	
	// todas as tags de um determinado artigo
	public void getTagsArtigo(String artigo) {
		
		Map<String, String> all =  jedis.hgetAll("art:" + artigo);
		
		System.out.println("\ntags do artigo: " + artigo);
		for(Map.Entry<String, String> elemento : all.entrySet()) {
			System.out.println(elemento.getKey());
		}
	}
	
	//imprindo nome e descricao de todos os artigos
	public void name_desc_all_artigos() {
		for(String srt : getTags()) {
			
			Map<String, String> all =  jedis.hgetAll("art:" + srt);
			
			for(Map.Entry<String, String> elemento : all.entrySet()) {
				
				if(elemento.getKey().equals("name"))
					System.out.println("Name: " + elemento.getValue());
				
				if(elemento.getKey().equals("description"))
					System.out.println("Description: " + elemento.getValue());
			}
		}
	}
	
	

	
}
