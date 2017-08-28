import java.io.*;

public class TesteEntradaIII {

    public static void main(String[] args) throws IOException {

        InputStream is = new FileInputStream("/home/naelio/IdeaProjects/PersistenciaArquivo/src/arquivoII.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String str;

        while ( (str =  br.readLine()) != null ){
            System.out.println(str);
        }

        br.close();
    }
}
