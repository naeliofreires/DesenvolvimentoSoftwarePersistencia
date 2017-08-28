import java.io.*;

public class TestarEntrada {

    public static void main(String[] args) throws IOException{


        InputStream is = new FileInputStream("/home/naelio/IdeaProjects/PersistenciaArquivo/src/arquivo.txt");

        //Criando um arquivo a qual vamos passar dados...
        OutputStream out = new FileOutputStream("/home/naelio/IdeaProjects/PersistenciaArquivo/src/arquivoII.txt");

        int l;

        while ( (l =  is.read()) != -1 ){
            // Escrevendo no arquivo criado ...
            out.write(l);
            System.out.print((char)l);
        }

        is.close();
        out.close();
    }
}
