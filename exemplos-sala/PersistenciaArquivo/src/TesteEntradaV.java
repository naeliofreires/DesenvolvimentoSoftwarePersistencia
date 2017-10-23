import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class TesteEntradaV {

    public static void main(String[] args) throws IOException{


        InputStream is = new FileInputStream("/home/naelio/IdeaProjects/PersistenciaArquivo/src/arquivoII.txt");

        try (Scanner sc = new Scanner(is)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }

    }
}
