import java.io.*;
import java.util.Scanner;

public class TesteEntradaIV {

    public static void main(String[] args) throws IOException {

        InputStream is = new FileInputStream("/home/naelio/IdeaProjects/PersistenciaArquivo/src/arquivoII.txt");

        Scanner sc = new Scanner(is);

        while (sc.hasNextLine() ){
            System.out.println(sc.nextLine());
        }

        sc.close();
    }
}
