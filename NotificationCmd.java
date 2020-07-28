import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NotificationCmd implements CommandExecutor {
    BlockNotifications Plugin;
    StaffManager PlayerData;
    ItemDataManager ItemData;
    public NotificationCmd(BlockNotifications plugin){
        Plugin = plugin;
        PlayerData = plugin.getPlayerData();
        ItemData = plugin.getItemData();
    }

    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String label, String[] args) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if (label.equalsIgnoreCase("Notify")) {
                if (args.length > 0) {
                    player.sendMessage(ChatColor.BOLD + "Block notifications is a toggle and requires no additional arguments.");
                }
                if (!this.PlayerData.getConfig().contains("players." + player.getName())) {
                    this.PlayerData.getConfig().set("players." + player.getName() + ".toggle", true);
                    player.sendMessage(ChatColor.GREEN + "Enabling Block Notifications.");
                    this.PlayerData.saveConfig();
                    return true;
                } else if (this.PlayerData.getConfig().getBoolean("players." + player.getName() + ".toggle")) {
                    this.PlayerData.getConfig().set("players." + player.getName() + ".toggle", false);
                    player.sendMessage(ChatColor.RED + "Disabling Block Notifications.");
                    this.PlayerData.saveConfig();
                    return false;
                } else {
                    this.PlayerData.getConfig().set("players." + player.getName() + ".toggle", true);
                    player.sendMessage(ChatColor.GREEN + "Enabling Block Notifications.");
                    this.PlayerData.saveConfig();
                    return true;
                }
            } else if (label.equalsIgnoreCase("BlockNotifications")) {
                if (args.length < 1) {
                    player.sendMessage("Please use the command /BlockNotifications reload");
                    return false;
                } else if (args[0].equalsIgnoreCase("reload")) {
                    ItemData.reloadConfig();
                    player.sendMessage("Reloading BlockNotifcations Config.");
                    return true;
                } else {
                    player.sendMessage("[BlockNotifications] Incorrect Args.");
                    return false;
                }
            }
        }
        return false;
    }
}
