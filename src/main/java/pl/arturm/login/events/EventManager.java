package pl.arturm.login.events;

import pl.arturm.login.Main;
import pl.arturm.login.events.impl.JoinEvent;
import pl.arturm.login.events.impl.MoveEvent;


public class EventManager {


    Main plugin;

    public EventManager(Main m) {
        this.plugin = m;
    }

    public void init() {

        plugin.getServer().getPluginManager().registerEvents(new JoinEvent(plugin), plugin);
        plugin.getServer().getPluginManager().registerEvents(new MoveEvent(plugin), plugin);
        plugin.getLogger().info("LoginPlugin | Event manager Loaded");
    }

}
