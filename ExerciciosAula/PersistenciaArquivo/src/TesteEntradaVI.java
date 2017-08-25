import java.io.*;
import java.util.Scanner;

public class TesteEntradaVI {

    public static void main(String[] args) throws IOException{

        InputStream is = TesteEntradaV.class.getResourceAsStream("/arquivo.txt");

        try (Scanner sc = new Scanner(is)) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        }

    }
}
