package fryzjer.server;

import java.net.ServerSocket;
import java.net.Socket;

public class NewThread implements Runnable {
    ListHolder listHolder;

    public NewThread(ListHolder listHolder) {
        this.listHolder = listHolder;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(2137);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                Thread inputThread = new Thread(new ServerHandler(this.listHolder, socket));
                inputThread.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}