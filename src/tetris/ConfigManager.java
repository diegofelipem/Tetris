package tetris;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class ConfigManager {

	private static final String CONFIG_FILE = "config.properties";
	private static final Properties PROP = new Properties();

	private ConfigManager() {

	}

	private static void init() {

		File file = new File(CONFIG_FILE);

		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			FileReader frConfig = new FileReader("config.properties");
			PROP.load(frConfig);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveConfig(String key, String value) {
		init();
		PROP.setProperty(key, value);
		try {
			PROP.store(new FileWriter(CONFIG_FILE), "saving " + key);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String loadConfig(String key) {
		init();
		if (PROP.containsKey(key)) {
			return PROP.getProperty(key);
		}
		return null;
	}
}
