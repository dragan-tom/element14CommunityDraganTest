package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    
    public static String chromeDriverPath;
    public static String startPage;
        
    public static void init() throws FileNotFoundException, IOException{
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("D:\\QA\\Workspace\\element14CommunityDraganTest\\Resources\\env.properties");        
        prop.load(fis);
        
        chromeDriverPath = prop.getProperty("chromeDriverPath");
        startPage = prop.getProperty("startPage");
                
    }
    
}
