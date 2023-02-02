package pl.arturm.login.librais.data;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class PlayerData {
    static PlayerData instance = new PlayerData();

    Plugin p;

    FileConfiguration data;

    public static File rfile;

    public static PlayerData getInstance() {
        return instance;
    }

    public void setup(Plugin p) {
        if (!p.getDataFolder().exists())
            p.getDataFolder().mkdir();
        File path = new File(p.getDataFolder() + File.separator + "/data");
        rfile = new File(path, String.valueOf(File.separatorChar) + "data.yml");
        if (!rfile.exists())
            try {
                path.mkdirs();
                rfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.DARK_RED + "[B" + ChatColor.GRAY + "Nie udasistworzypliku data.yml !");
            }
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(rfile);
    }

    public FileConfiguration getData() {
        return this.data;
    }

    public void saveData() {
        try {
            this.data.save(rfile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "[B" + ChatColor.GRAY + "Nie udasizapisapliku data.yml !");
        }
    }

    public void reloadData() {
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(rfile);
    }
}
