package fryzjer.client;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;

public class NewClient implements Runnable {
    private static final int DATATONEWCLIENT = 83;
    DataInputStream dIS;
    DataOutputStream dOS;
    Handler handler;

    public NewClient(DataInputStream dIS, DataOutputStream dOS, Handler handler) {
        this.dIS = dIS;
        this.dOS = dOS;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Platform.runLater(() -> {
                    try {
                        handler.ServerList.clear();
                        dOS.writeInt(DATATONEWCLIENT);
                        int reservation = dIS.readInt();
                        for (int i = 0; i < reservation; i++) {
                            String name = dIS.readUTF();
                            String date = dIS.readUTF();
                            String time = dIS.readUTF();
                            Reservation newClient = new Reservation(name, LocalDate.parse(date), LocalTime.parse(time));
                            handler.ServerList.add(newClient);
                        }

                        handler.ListOfAllReservation.getItems().clear();

                        for (Reservation client : handler.ServerList) {
                            handler.ListOfAllReservation.getItems().addAll(client.toString());
                        }
                    } catch (Exception ignored) {
                    }
                });
                Thread.sleep(1000);
            }
        } catch (Exception ignored) {
        }
    }
}
