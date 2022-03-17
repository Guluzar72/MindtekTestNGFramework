package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        String path = "/Users/guluzaryourieva/IdeaProjects/MindtekTestNGFramework/src/test/resources/configuration/Configuration.properties";
        FileInputStream input=null;

        try {
            input = new FileInputStream(path);
             properties = new Properties();
            properties.load(input);
        } catch (FileNotFoundException e) {
            System.out.println("Properties file path is invalid");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

            System.out.println("Failed to load properties file");
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                System.out.println("Exception thrown while closing FileInputStream object");
            }
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);

    }


}
