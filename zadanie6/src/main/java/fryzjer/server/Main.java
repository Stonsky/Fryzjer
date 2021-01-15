package fryzjer.server;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Thread thread = new Thread(new NewThread(new ListHolder()));
        thread.start();
        System.out.println("server dziala");
    }
}