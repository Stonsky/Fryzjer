package fryzjer.server;

import javafx.application.Platform;
import fryzjer.client.Reservation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;

public class ServerHandler implements Runnable {
    private static final int ERROR = 45;
    ListHolder listHolder;
    Socket socket;
    public final int RESERVATION = 0;
    public final int DATATONEWCLIENT = 83;
    public final int DELETERESERVATION = 1;

    public ServerHandler(ListHolder listHolder, Socket socket) {
        this.listHolder = listHolder;
        this.socket = socket;
    }
    int ListCheck(Reservation newClient) {
        for (Reservation temp : listHolder.ServerList) {
            if (temp.getDate().isEqual(newClient.getDate()) && temp.getHour().getHour() == newClient.getHour().getHour()) {
                return ERROR;
            }
        }
        return 0;
    }

    @Override
    public void run() {
        try {
            DataInputStream dIS = new DataInputStream(socket.getInputStream());
            DataOutputStream dOS = new DataOutputStream(socket.getOutputStream());
            String name, date, hour;
            int whattodo;
            while (true) {
                whattodo = dIS.readInt();
                if (whattodo == RESERVATION) {
                    name = dIS.readUTF();
                    date = dIS.readUTF();
                    hour = dIS.readUTF();
                    Reservation newClient = new Reservation(name, LocalDate.parse(date), LocalTime.parse(hour));
                    if (ListCheck(newClient) == ERROR) {
                        dOS.writeInt(ERROR);
                        continue;
                    }
                    listHolder.ServerList.add(newClient);
                    dOS.writeInt(0);
                                    }
                if (whattodo == DELETERESERVATION) {
                    name = dIS.readUTF();
                    date = dIS.readUTF();
                    hour = dIS.readUTF();
                    Reservation newClient = new Reservation(name, LocalDate.parse(date), LocalTime.parse(hour));
                    for (Reservation pom : listHolder.ServerList) {
                        if (pom.toString().equals(newClient.toString())) {
                            listHolder.ServerList.remove(pom);
                            break;
                        }
                    }
                    dOS.writeInt(0);
                                    }
                if (whattodo == DATATONEWCLIENT) {
                    dOS.writeInt(listHolder.ServerList.size());
                    for (Reservation temp : listHolder.ServerList) {
                        dOS.writeUTF(temp.getName());
                        dOS.writeUTF(temp.getDate().toString());
                        dOS.writeUTF(temp.getHour().toString());
                    }
                }
            }
        } catch (IOException ignored) {
        }
    }

}