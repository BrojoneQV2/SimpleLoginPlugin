package pl.arturm.login.cmds.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import pl.arturm.login.Main;
import pl.arturm.login.librais.data.PlayerData;
import pl.arturm.login.librais.login.LoginUser;
import pl.arturm.login.librais.login.Sessions;

import java.util.Objects;

public class Register implements CommandExecutor {

    Main plugin;
    PlayerData pd = PlayerData.getInstance();
    public Register(Main m) {
        this.plugin = m;
        m.getCommand("register").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (Sessions.getUserByUUID(p.getUniqueId()) != null ||  pd.getData().getBoolean("" + p.getUniqueId() + ".registered")) {
                p.sendMessage(" ");
                p.sendMessage("§fPl: §aJesteś zarejestrowany.");
                p.sendMessage(" ");
            } else if (args.length != 1) {
                p.sendMessage(" ");
                p.sendMessage("§fPl: §aPodaj Hasło.");
                p.sendMessage(" ");
            } else {
                PlayerData.getInstance().getData().set("" + p.getUniqueId() + ".registered", true);
                PlayerData.getInstance().getData().set("" + p.getUniqueId() + ".password",args[0]);
                PlayerData.getInstance().saveData();
                p.sendMessage(" ");
                p.sendMessage("§fPl: §aZostałeś zarejestrowany.");
                p.sendMessage(" ");
                Sessions.addUser(new LoginUser(p));
                p.removePotionEffect(PotionEffectType.BLINDNESS);
                for(Player on : Bukkit.getOnlinePlayers()) {
                    if(!(Objects.equals(on.getName(), p.getName()))) {
                        p.showPlayer(on);
                    }
                }
            }
        }

        return false;
    }
}
