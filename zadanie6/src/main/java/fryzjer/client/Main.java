package fryzjer.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Client.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 310, 425));
        primaryStage.show();
        Handler handler = loader.getController();
        handler.connect();
        handler.hour.getItems().addAll("11:00", "12:00", "13:00", "14:00", "15:00", "16:00");
    }
    }
