package fryzjer.client;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class ReservationTest {
    Reservation reservation = new Reservation("Name", LocalDate.parse("2000-12-12"), LocalTime.parse("21:37"));
    String name;
    String expectedName = "Name";
    LocalDate expectedlocalDate = LocalDate.parse("2000-12-12");
    LocalDate localDate = null;
    LocalTime expectedlocalTime = LocalTime.parse("21:37");
    LocalTime localTime = null;
    String expectedString = "name='Name', date=2000-12-12, hour=21:37";
    String string;
    @Test
    void getName() {
        name = reservation.getName();
        assertEquals(expectedName,name);
    }

    @Test
    void getDate() {
        localDate = reservation.getDate();
        assertEquals(expectedlocalDate,localDate);
    }

    @Test
    void getHour() {
        localTime = reservation.getHour();
        assertEquals(expectedlocalTime,localTime);
    }

    @Test
    void testToString() {
        string = reservation.toString();
        assertEquals(expectedString,string);
    }
}