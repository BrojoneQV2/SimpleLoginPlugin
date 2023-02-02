package pl.arturm.login.events.impl;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.arturm.login.Main;
import pl.arturm.login.librais.login.Sessions;

public class MoveEvent implements Listener {

    Main plugin;
    Location Spawn;
    public MoveEvent(Main m) {
        this.plugin =m;
       Spawn = plugin.getConfig().getLocation("spawn.cords");
    }





    @EventHandler
    public void onPlayerMoves(PlayerMoveEvent e) {
        if(Sessions.getUserByUUID(e.getPlayer().getUniqueId()) == null) {
            e.getPlayer().teleport(Spawn);
        }
        if(e.getPlayer().getLocation().getY() <= plugin.getConfig().getDouble("spawn.minY") ){
            e.getPlayer().teleport(Spawn);
        }
    }
}
