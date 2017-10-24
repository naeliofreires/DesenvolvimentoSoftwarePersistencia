package com.persistencia.csv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class CsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvApplication.class, args);

		Properties prop = new Properties();

		try {
            prop.setProperty("caminhoArquivo", System.getProperty("user.home") + "/contatos.csv");
			prop.store(new FileOutputStream(System.getProperty("user.home") + "/config.properties"), null);
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
}
