package com.persistencia.csv.controller;

import com.persistencia.csv.model.Contato;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

@RestController
public class ContatoController {

    private Properties prop = new Properties();
    private Scanner entrada	=	new	Scanner(System.in);

    @RequestMapping(value = "/contacts/load", method = RequestMethod.GET, produces="application/json")
    public ArrayList<Contato> listar() {


        ArrayList<Contato> allContatosCSV = null;

        try {
            // carregando o arquivo config.properties
            prop.load(new FileInputStream(System.getProperty("user.home") + "/config.properties"));
            InputStream is = new FileInputStream(prop.getProperty("caminhoArquivo"));
            entrada = new Scanner(is);

            allContatosCSV = new ArrayList<>();

            while (entrada.hasNextLine()) {
                String [] usr = entrada.nextLine().split(",");
                allContatosCSV.add(getUser(usr));

            }

            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return allContatosCSV;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addContact(@RequestBody Contato contato){

        try {

            OutputStream os = new FileOutputStream(prop.getProperty("caminhoArquivo"), true);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(contato.getNome() + ','
                    + contato.getEndereco() + ','
                    + contato.getTelefone() + ','
                    + contato.getEmail());
            bw.newLine();
            bw.close();

            System.out.println("done!");

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private Contato getUser(String [] line){
        return new Contato(line[0], line[1], line[2], line[3]);
    }
}
