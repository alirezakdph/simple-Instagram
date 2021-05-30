package Server.Controller;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerJsonHandler
{
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private Gson gson;

    ServerJsonHandler () throws IOException {
        serverSocket = new ServerSocket(6000);
        socket = serverSocket.accept();
        in = new DataInputStream( socket.getInputStream() );
        gson = new Gson();
    }

    Request recieveFromClient () throws IOException {
        byte[] bytes = new byte[in.readInt()];
        in.readFully(bytes);
        String json = new String(bytes);
        return gson.fromJson(json, Request.class);
    }

}
