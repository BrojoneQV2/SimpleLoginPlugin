package pl.arturm.login.events.impl;

import org.apache.commons.lang.time.DateUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.arturm.login.Main;
import pl.arturm.login.librais.data.PlayerData;
import pl.arturm.login.librais.login.LoginUser;
import pl.arturm.login.librais.login.Sessions;

import java.util.Date;
import java.util.Objects;

public class JoinEvent implements Listener {

    Main plugin;
    PlayerData pd = PlayerData.getInstance();
    Location Spawn;


    public JoinEvent(Main m) {
        this.plugin = m;
        Spawn = plugin.getConfig().getLocation("spawn.cords");
    }

    @EventHandler
    public void JoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 1 ));
        for(Player on : Bukkit.getOnlinePlayers()) {
            if(!(Objects.equals(on.getName(), p.getName()))) {
                p.hidePlayer(on);
            }
        }
        p.teleport(Spawn);
        LoginUser us = Sessions.getUserByUUID(p.getUniqueId());
        if(us == null) {
            if(!pd.getData().getBoolean("" + p.getUniqueId() + ".registered")) {
                p.sendMessage("");
                p.sendMessage("§fPl: §aProsimy o zarejestrowanie się.");
                p.sendMessage("§aUżyj komendy: §7/register <haslo>");
                p.sendMessage("");
            } else {
                p.sendMessage("§fPl: §aProsimy o zalogowanie się.");
                p.sendMessage("§aUżyj komendy: §7/login <haslo>");
                p.sendMessage("");
            }
        } else {
            Date old = us.getLoggedTime();
            Date newDate = DateUtils.addHours(old, 3);
            if((newDate.equals(new Date(System.currentTimeMillis())) || newDate.after(new Date(System.currentTimeMillis()))) && Objects.equals(us.getIPAdress(), p.getAddress().getHostString())) {
                p.sendMessage("");
                p.sendMessage("§fPl: §aSesja Logowania Aktywna");
                p.sendMessage("");
                p.removePotionEffect(PotionEffectType.BLINDNESS);
                for(Player on : Bukkit.getOnlinePlayers()) {
                    if(!(Objects.equals(on.getName(), p.getName()))) {
                        p.showPlayer(on);
                    }
                }

            } else {
                Sessions.removeUser(us);
                if(!pd.getData().getBoolean("" + p.getUniqueId() + ".registered")) {
                    p.sendMessage("");
                    p.sendMessage("§fPl: §aProsimy o zarejestrowanie się.");
                    p.sendMessage("§aUżyj komendy: §7/register <haslo>");
                    p.sendMessage("");
                } else {
                    p.sendMessage("");
                    p.sendMessage("§fPl: §aProsimy o zalogowanie się.");
                    p.sendMessage("§aUżyj komendy: §7/login <haslo>");
                    p.sendMessage("");
                }

            }
        }



    }

}
