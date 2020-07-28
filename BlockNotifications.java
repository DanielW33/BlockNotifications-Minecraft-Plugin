import org.bukkit.plugin.java.JavaPlugin;

public class BlockNotifications extends JavaPlugin {
    private StaffManager PlayerData;
    private ItemDataManager ItemData;
    @Override
    public void onEnable(){
        this.PlayerData = new StaffManager(this);
        this.ItemData = new ItemDataManager(this);

        this.PlayerData.getConfig().options().copyDefaults(false);
        this.PlayerData.saveDefaultConfig();

        this.ItemData.getConfig().options().copyDefaults(false);
        this.ItemData.saveDefaultConfig();

        this.getCommand("Notify").setExecutor(new NotificationCmd(this));

        this.getServer().getPluginManager().registerEvents(new onBlockBreak(this), this);
        this.getServer().getPluginManager().registerEvents(new onBlockPickup(this), this);
        this.getServer().getPluginManager().registerEvents(new onBlockPlace(this), this);
        this.getServer().getPluginManager().registerEvents(new onBlockThrow(this), this);
    }
    public StaffManager getPlayerData(){
        return PlayerData;
    }
    public ItemDataManager getItemData(){
        return ItemData;
    }
}
