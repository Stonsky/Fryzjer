package fryzjer.client;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Handler {
    public List<Reservation> PersonalList = new ArrayList<>();
    public List<Reservation> ServerList = new ArrayList<>();
    int ERROR = 45;
    public final int DELETERESERVATION = 1;

    public TextField name;
    public Label errorbox;
    public ChoiceBox<String> hour;
    public DatePicker date;
    public Button ReserveButton;
    public Button CancelButton;

    Socket socket;
    DataOutputStream dOS;
    DataInputStream dIS;
    public ListView<String> ListOfAllReservation;
    public ListView<String> ListofYourReservation;
    int CheckCorrectness(String name, LocalDate localDate) {
        errorbox.setText("");
        if (name.equals("") || localDate == null || localDate.isBefore(LocalDate.now()) || hour.getValue() == null) {
            errorbox.setText("Something's wrong, I can feel it");
            return ERROR;
        }
        return 0;
    }
    public void connect() {
        try {
            this.socket = new Socket("localhost", 2137);
            dOS = new DataOutputStream(socket.getOutputStream());
            dIS = new DataInputStream(socket.getInputStream());
            Thread thread = new Thread(new NewClient(dIS, dOS, this));
            thread.start();
        } catch (Exception e) {
        }
    }
    public void Reserve(ActionEvent actionEvent) {
        if (CheckCorrectness(name.getText(), date.getValue()) != 0)
            return;
        Reservation newClient = new Reservation(name.getText(), date.getValue(), LocalTime.parse(hour.getValue()));
        if (Exchange(newClient, 0) == ERROR)
            return;
        PersonalList.add(newClient);
        ListofYourReservation.getItems().clear();
        for (Reservation client : PersonalList) {
            ListofYourReservation.getItems().add(client.toString());
        }
    }
    public void Cancel(ActionEvent actionEvent) {
        String cancel = ListofYourReservation.getSelectionModel().getSelectedItem();
        ListofYourReservation.getItems().removeAll(cancel);
        Reservation toCancel = null;
        for (Reservation temp : PersonalList) {
            if (temp.toString().equals(cancel)) {
                toCancel = temp;
                PersonalList.remove(temp);
                break;
            }
        }
        if (toCancel == null)
            return;
        Exchange(toCancel, DELETERESERVATION);
    }

    int Exchange(Reservation newClient, int whattodo) {
        try {
            dOS.writeInt(whattodo);
            dOS.writeUTF(newClient.getName());
            dOS.writeUTF(newClient.getDate().toString());
            dOS.writeUTF(newClient.getHour().toString());
            return dIS.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    


}