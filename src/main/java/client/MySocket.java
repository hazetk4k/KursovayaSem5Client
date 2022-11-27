package client;

import java.net.InetAddress;
import java.net.Socket;

public final class MySocket {

    public static final MySocket INSTANCE = new MySocket();
    private static Socket sock;

    public static void setSock(Socket sock) {
        MySocket.sock = sock;
    }

    public Socket getSock() {
        return sock;
    }

}
