package fryzjer.server;

import fryzjer.client.Reservation;
import org.junit.jupiter.api.*;
import fryzjer.server.ListHolder;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ServerHandlerTest {
    ListHolder listHolder = new ListHolder();
    Socket socket = new Socket();
    ServerHandler serverHandler = new ServerHandler(listHolder, socket);
    Reservation reservation = new Reservation("Name", LocalDate.parse("2000-12-12"), LocalTime.parse("21:37"));
    Reservation reservation1 = new Reservation("Name1", LocalDate.parse("2000-12-13"), LocalTime.parse("22:37"));
    Reservation reservation2 = new Reservation("Name2", LocalDate.parse("2000-12-14"), LocalTime.parse("23:37"));

    @BeforeEach
    void nazwa(){
        listHolder.ServerList.add(reservation);
        listHolder.ServerList.add(reservation1);
    }
    @AfterEach
    void clear(){
        listHolder.ServerList.clear();
    }
    @Test
    void listCheck() {
        assertEquals(45,serverHandler.ListCheck(this.reservation));
    }
    @Test
    void listCheck1() {
        assertEquals(0,serverHandler.ListCheck(this.reservation2));
    }

}