package nl.thedutchmc.DutchyMotd;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import nl.thedutchmc.DutchyMotd.EventListeners.PlayerLoginEventListener;

public class DutchyMotd extends JavaPlugin {

	public static DutchyMotd INSTANCE;
	public static ConfigurationHandler configHandler;
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		configHandler = new ConfigurationHandler();
		
		configHandler.loadConfig();

		if(!this.isEnabled()) return;
		
		Bukkit.getPluginManager().registerEvents(new PlayerLoginEventListener(), this);
	}
	
}
