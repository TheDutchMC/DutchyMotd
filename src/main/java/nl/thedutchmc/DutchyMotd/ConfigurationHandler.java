package nl.thedutchmc.DutchyMotd;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationHandler {

	public static String motd, motdBaseColor, motdVariableColor;
	public static boolean motdEnabled;
	
	private File file;
	private FileConfiguration config;
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void loadConfig() {
		
		file = new File(DutchyMotd.INSTANCE.getDataFolder(), "config.yml");
		
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			DutchyMotd.INSTANCE.saveResource("config.yml", false);
		}
		
		config = new YamlConfiguration();
		
		try {
			config.load(file);
			readConfig();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			System.err.println("[DutchyMotd] Your config is invalid!");
			Bukkit.getPluginManager().disablePlugin(DutchyMotd.INSTANCE);
		}
	}
	
	public void readConfig() {
		motd = this.getConfig().getString("motd");
		motdBaseColor = this.getConfig().getString("motdBaseColor");
		motdVariableColor = this.getConfig().getString("motdVariableColor");
		
		motdEnabled = Boolean.valueOf(this.getConfig().getString("motdEnabled"));
	}
}
