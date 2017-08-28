import java.io.*;

public class TesteEntradaII {

    public static void main(String[] args) throws IOException {

        InputStream is = new FileInputStream("/home/naelio/IdeaProjects/PersistenciaArquivo/src/arquivoII.txt");
        InputStreamReader isr = new InputStreamReader(is);

        int l;

        while ( (l =  isr.read()) != -1 ){
            System.out.print((char)l);
        }

        is.close();
    }
}
