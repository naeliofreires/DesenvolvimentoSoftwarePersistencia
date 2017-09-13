import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Commands {

    private Properties properties;
    private UploadFile uploadFile;
    private Scanner entrada;

    public Commands() {
        this.properties = new Properties();
        this.uploadFile = new UploadFile();
        this.entrada = new Scanner(System.in);
    }

    public void cat() throws IOException {

        long start = System.currentTimeMillis();

        if (!this.uploadFile.fileExists())
            this.uploadFile.createFile();
        this.properties.load(new FileInputStream(System.getProperty("user.home") + "/config.properties"));
        InputStream is = new FileInputStream(this.properties.getProperty("file-path"));
        entrada = new Scanner(is);
        int numberOfLines = 0;
        while (entrada.hasNextLine()) {
            System.out.println(entrada.nextLine());
            numberOfLines++;
        }

        long elapsed = System.currentTimeMillis() - start;

        System.out.println("____________________' \nend of file");
        System.out.println("number of lines: " + numberOfLines);
        System.out.println("duration " + elapsed + " mls");
    }

    public void head() throws IOException{

        long start = System.currentTimeMillis();

        if (!this.uploadFile.fileExists())
            this.uploadFile.createFile();
        this.properties.load(new FileInputStream(System.getProperty("user.home") + "/config.properties"));
        InputStream is = new FileInputStream(this.properties.getProperty("file-path"));
        entrada = new Scanner(is);
        int numberLine = 1;
        while (entrada.hasNextLine()) {
            System.out.println(numberLine + ": " + entrada.nextLine());
            if(numberLine == 10)
                break;
            numberLine++;
        }

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("____________________' \nend of file");
        System.out.println("duration " + elapsed + " mls");
    }

    public void wc() throws IOException {

        long start = System.currentTimeMillis();

        if (!this.uploadFile.fileExists())
            this.uploadFile.createFile();
        this.properties.load(new FileInputStream(System.getProperty("user.home") + "/config.properties"));
        InputStream is = new FileInputStream(this.properties.getProperty("file-path"));
        entrada = new Scanner(is);

        int numberOfLines = 0;
        Integer numberOfChars = 0;
        int numberOfWords = 0;

        while (entrada.hasNextLine()) {
            numberOfLines++;
            String word = entrada.nextLine();
            numberOfChars += word.getBytes().length;
            String[] separateWords = word.split(",");
            numberOfWords += separateWords.length;
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("____________________' \nend of file");
        System.out.println("number of lines: " + numberOfLines);
        System.out.println("number of words: " + numberOfWords);
        System.out.println("number of chars: " + numberOfChars);
        System.out.println("duration " + elapsed + " mls");
    }

    public void turnIntoBinary(String nameFile) throws IOException{

        long start = System.currentTimeMillis();

        try {
            if (!this.uploadFile.fileExists())
                this.uploadFile.createFile();
            this.properties.load(new FileInputStream(System.getProperty("user.home") + "/config.properties"));
            InputStream is = new FileInputStream(this.properties.getProperty("file-path"));
            entrada = new Scanner(is);

            PrintStream new_file = new PrintStream(System.getProperty("user.home") + "/" + nameFile + ".bin");

            while (entrada.hasNextLine()) {
                byte[] letra = entrada.nextLine().getBytes();
                new_file.println(letra);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("duration " + elapsed + " mls");
    }

}
