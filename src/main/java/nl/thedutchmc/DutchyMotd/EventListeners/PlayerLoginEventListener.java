package nl.thedutchmc.DutchyMotd.EventListeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import net.md_5.bungee.api.ChatColor;
import nl.thedutchmc.DutchyMotd.ConfigurationHandler;

public class PlayerLoginEventListener implements Listener {

	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event) {
		
		if(!ConfigurationHandler.motdEnabled) return;
		
		String motd = ConfigurationHandler.motd;
		
		final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		String timeNow = formatter.format(new Date());
		
		final ChatColor baseColor = ChatColor.of("#" + ConfigurationHandler.motdBaseColor);
		final ChatColor variableColor = ChatColor.of("#" + ConfigurationHandler.motdVariableColor);
		
		motd = motd
			.replace("{PLAYER}", variableColor + event.getPlayer().getName() + baseColor)
			.replace("{CURRENT_TIME}", variableColor + timeNow + baseColor);
		
		motd = baseColor + motd;
		
		event.getPlayer().sendMessage(motd);
		
	}
	
}
