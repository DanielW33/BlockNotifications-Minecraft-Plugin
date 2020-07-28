import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class onBlockPlace implements Listener {

    BlockNotifications Plugin;
    StaffManager PlayerData;
    ItemDataManager ItemData;

    public onBlockPlace(BlockNotifications plugin){
        Plugin = plugin;
        PlayerData = plugin.getPlayerData();
        ItemData = plugin.getItemData();
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent Event) {
        if (!this.PlayerData.getConfig().contains("players." + Event.getPlayer().getName())) {
            List<String> BlockList = this.ItemData.getConfig().getStringList("Blocks.BlockMaterial");
            for (int i = 0; i < BlockList.size(); i++) {
                if (Material.matchMaterial(BlockList.get(i)).equals(Event.getBlock().getType())) {
                    this.PlayerData.getConfig().getConfigurationSection("players.").getKeys(false).forEach(Staff -> {
                        if(this.PlayerData.getConfig().getBoolean("players." + Staff + ".toggle")) {
                            Player player = Bukkit.getPlayer(Staff);
                            Player Member = Event.getPlayer();
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7" + Member.getDisplayName() + " &7placed a " + Event.getBlock().getType()));
                        }
                    });
                    return;
                }
            }
        }
    }
}
