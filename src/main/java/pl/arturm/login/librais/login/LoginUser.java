package pl.arturm.login.librais.login;

import org.bukkit.entity.Player;

import java.util.Date;

public class LoginUser {

    private Player player;
    private Date LoggedTime;
    private String IPAdress;

    public LoginUser(Player player) {
        this.player = player;
        this.LoggedTime = new Date(System.currentTimeMillis());
        this.IPAdress =  player.getAddress().getHostString();
    }


    public Player getPlayer() {
        return player;
    }

    public Date getLoggedTime() {
        return LoggedTime;
    }

    public String getIPAdress() {
        return IPAdress;
    }

}
