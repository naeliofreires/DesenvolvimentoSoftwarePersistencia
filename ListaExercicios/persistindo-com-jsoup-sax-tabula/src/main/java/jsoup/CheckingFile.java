package jsoup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

// class to check if the file exists, otherwise I will create the file
public class CheckingFile {

    private Properties properties;

    public CheckingFile(){
        this.properties = new Properties();
    }

    public Properties getProperties() {
        return properties;
    }

    public boolean fileExists() throws IOException{
        try {
            properties.load(new FileInputStream(System.getProperty("user.home") + "/config.properties"));
            System.out.println("file found");
            return true;
        }catch (FileNotFoundException ex){
            System.out.println("file not found");
            return false;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean createFile() throws IOException{
        try{
            if(!fileExists()){
                properties.setProperty("file-path", System.getProperty("user.home") + "/data-store.csv");
                properties.store(new FileOutputStream(System.getProperty("user.home") + "/config.properties"), null);
                System.out.println("file config.properties and data-store have been created\n");
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
