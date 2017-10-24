import org.apache.hadoop.util.ToolRunner;

import java.util.Properties;

public class App {
    public static void main(String[] args) {
        try {
            Properties properties;
            properties = new Properties();
            properties.load(App.class.getClassLoader().getResourceAsStream("config.properties"));
            String pathInput = properties.getProperty("pathInput");
            String pathOutput = properties.getProperty("pathOutput");

            int exitCode = ToolRunner.run(new WordCount(), new String[]{pathInput, pathOutput});
            System.exit(exitCode);
        }catch (Exception ex){ex.printStackTrace();}
    }
}
