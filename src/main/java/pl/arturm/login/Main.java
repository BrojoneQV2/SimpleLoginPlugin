package pl.arturm.login;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import pl.arturm.login.cmds.CmdManager;
import pl.arturm.login.events.EventManager;
import pl.arturm.login.librais.data.PlayerData;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
      new EventManager(this).init();
      new CmdManager(this).init();
      getConfig().addDefault("spawn.cords", new Location(Bukkit.getWorld("world"), 0, 60, 0));
      getConfig().addDefault("spawn.minY", 30);
      getConfig().options().copyDefaults(true);
      saveConfig();
      PlayerData.getInstance().setup(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
