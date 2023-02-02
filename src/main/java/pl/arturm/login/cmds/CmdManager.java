package pl.arturm.login.cmds;

import pl.arturm.login.Main;
import pl.arturm.login.cmds.impl.Login;
import pl.arturm.login.cmds.impl.Register;

public class CmdManager {


    Main plugin;

    public CmdManager(Main m) {
        this.plugin = m;
   }

   public void init() {
    new Login(plugin);
    new Register(plugin);
    plugin.getLogger().info("LoginPlugin | Command manager Loaded");

   }


}
