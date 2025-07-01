package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertiesFile {

	private FileInputStream fis;
	private Properties properties;
	private String basePath = System.getProperty("user.dir");
	public ReadPropertiesFile(String path) {
		try {
			fis = new FileInputStream(basePath+File.separator+path);
			properties = new Properties();
			properties.load(fis);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String getPropertyKey(String key) {

		return properties.getProperty(key);
	}
}
