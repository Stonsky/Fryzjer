package fryzjer.client;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private String name;
    private LocalDate date;
    private LocalTime hour;

    public Reservation(String name, LocalDate date, LocalTime hour) {
        this.name = name;
        this.date = date;
        this.hour = hour;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getHour() {
        return hour;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                        ", date=" + date +
                        ", hour=" + hour;
    }
}
