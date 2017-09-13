import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

//  class to check if the file exists, otherwise I will create the file.
public class UploadFile {

    Properties properties;

    public UploadFile() {
        this.properties = new Properties();
    }

    public boolean fileExists() throws IOException {
        try {
            properties.load(new FileInputStream(System.getProperty("user.home") + "/config.properties"));
            System.out.println("file found");
            return true;
        }catch (FileNotFoundException ex){
            System.out.println("file not found");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createFile() throws IOException {
        if(!fileExists()){
            properties.setProperty("file-path", System.getProperty("user.home") + "/plasma.csv");
            properties.store(new FileOutputStream(System.getProperty("user.home") + "/config.properties"), null);
            System.out.println("file config.properties create");
            return  true;
        }
        return false;
    }

}
