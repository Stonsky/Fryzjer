package fryzjer.client;

import com.sun.glass.ui.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HandlerTest extends ApplicationTest {
   Handler handler;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Client.fxml"));
        loader.load();
        this.handler = loader.getController();
        this.handler.hour.setValue("21:37");
    }
    @DisplayName("Correct")
    @Test
    void checkCorrectness() {
        assertEquals(0,  handler.CheckCorrectness("Name", LocalDate.parse("2021-12-12")));
    }
    @DisplayName("Name is null")
    @Test
    void checkCorrectness2() {
        assertEquals(45,  handler.CheckCorrectness("", LocalDate.parse("2021-12-12")));
    }
    @DisplayName("Date is null")
    @Test
    void checkCorrectness3() {
        assertEquals(45,  handler.CheckCorrectness("Name", null));
    }
    @DisplayName("Date before today")
    @Test
    void checkCorrectness4() {
        assertEquals(45,  handler.CheckCorrectness("Name", LocalDate.parse("2020-12-12")));
    }
    @DisplayName("Hour is null")
    @Test
    void checkCorrectness5() {
        this.handler.hour.setValue(null);
        assertEquals(45,  handler.CheckCorrectness("Name", LocalDate.parse("2021-12-12")));
    }

}