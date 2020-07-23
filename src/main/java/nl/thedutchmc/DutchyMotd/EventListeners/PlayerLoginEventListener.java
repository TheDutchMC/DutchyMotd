package nl.thedutchmc.DutchyMotd.EventListeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.DutchyMotd.ConfigurationHandler;
import nl.thedutchmc.DutchyMotd.DutchyMotd;

public class PlayerLoginEventListener implements Listener {

	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event) {
		
		if(!ConfigurationHandler.motdEnabled) return;
		
		new BukkitRunnable() {
			
			String motd = ConfigurationHandler.motd;
			String timeNow = "";
			
			@Override
			public void run() {
				for(World w : Bukkit.getWorlds()) {
					if(w.getEnvironment().equals(Environment.NORMAL)) {
						
					long hours = w.getTime() / 1000;
					long minutes = w.getTime() % 1000 / 60;
					timeNow = (hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes;
					}
				}
				
				final ChatColor baseColor = ChatColor.of("#" + ConfigurationHandler.motdBaseColor);
				final ChatColor variableColor = ChatColor.of("#" + ConfigurationHandler.motdVariableColor);
				
				motd = motd
					.replace("{PLAYER}", variableColor + event.getPlayer().getName() + baseColor)
					.replace("{TIME}", variableColor + timeNow + baseColor);
				
				motd = baseColor + motd;
							
				event.getPlayer().sendMessage(motd);
				
			}
		}.runTaskLater(DutchyMotd.INSTANCE, 1);
		
		
	}
	
}
