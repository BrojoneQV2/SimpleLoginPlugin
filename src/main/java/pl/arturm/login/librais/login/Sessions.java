package pl.arturm.login.librais.login;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Sessions {
    public static ConcurrentLinkedQueue<LoginUser> Logged = new ConcurrentLinkedQueue<>();

    public static LoginUser getUserByUUID(UUID PlayerUUID) {
        LoginUser l = null;
        for (LoginUser us : Logged) {
            if (us.getPlayer().getUniqueId().equals(PlayerUUID)) {
                l = us;
            }
        }
        return l;
    }

    public static void addUser(LoginUser us) {
        Logged.add(us);
    }

    public static void removeUser(LoginUser us) {
            Logged.remove(us);

    }

}
