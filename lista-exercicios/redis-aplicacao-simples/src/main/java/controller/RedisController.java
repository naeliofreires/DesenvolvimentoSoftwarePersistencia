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
	public void getTags() {
		Set<String> alltags = jedis.smembers("all:art");
		for(String srt : alltags)
			System.out.println(srt);
	}
	
	// todas as tags de um determinado artigo
	public void getTagsArtigo(String artigo) {
		Map<String, String> all =  jedis.hgetAll(artigo);
		
		for(Map.Entry<String, String> elemento : all.entrySet()) {
			System.out.println(elemento.getKey());
		}
	}

	
}
