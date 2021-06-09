package Server.Controller;

import Server.Model.User;
import Server.Utils;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainManager
{
    public static List<ActiveClient> activeClientList = new ArrayList<>();

    public static void addNewClient(ActiveClient client) {
        activeClientList.add(client);
    }

    public synchronized static void removeClient(NetworkManager networkManager) {
        activeClientList.removeIf(client -> client.getNetworkManager().equals(networkManager));
    }


    public synchronized static Response process(Request req, AtomicBoolean state) {
        {
            Data dat = null;
            User user = null;
            boolean flag;

            switch ( req.getTitle() )
            {
                case "signup":
                    dat = req.getData();
                    flag = DatabaseManager.checkUsername(Utils.LOGIN , dat.clientUsername);

                    if (!flag) {
                        user = new User(dat.clientUsername, dat.dataString );
                        DatabaseManager.adduser(user);
                    }
                    return new Response("signup", new Data.BooleanData(dat.clientUsername, flag) );

                case "login":
                    dat = req.getData();
                    user = new User(dat.clientUsername, dat.dataString );
                    flag = DatabaseManager.checkLogin(user);
                    return new Response("login", new Data.BooleanData(dat.clientUsername, flag) );

                case "terminate":
                    state.set(false);
            }

            return null;

            //each Request.title corresponds to a case in the switch statement which summons a method from mongo
            //to process the Request. These methods should all return a Response object which is sent back to the client.
        }
    }

}
