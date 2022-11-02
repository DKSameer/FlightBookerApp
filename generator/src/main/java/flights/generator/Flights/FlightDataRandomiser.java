package flights.generator.Flights;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class FlightDataRandomiser {

    public LocalDate date(LocalDate inputDate) {
        LocalDate tempDate = inputDate;
        if (Math.ceil(Math.random()) == 1) {
            tempDate.minusDays((int) Math.floor(Math.random() * 3.99));
        } else {
            tempDate.plusDays((int) Math.floor(Math.random() * 3.99));
        }
        return tempDate;
    }

    public LocalTime departureTime() {

        int hour = (int) Math.floor(Math.random() * 24);
        LocalTime tempTime = LocalTime.of(hour, 00);

        int minutesRandomizer = (int) Math.floor(Math.random() * 4.99);
        switch (minutesRandomizer) {
            case 0:
                tempTime = tempTime.plusMinutes(10);
            case 1:
                tempTime = tempTime.plusMinutes(10);
            case 2:
                tempTime = tempTime.plusMinutes(10);
            case 3:
                tempTime = tempTime.plusMinutes(10);
            case 4:
                tempTime = tempTime.plusMinutes(10);
        }
        return tempTime;
    }

    public boolean luggageAllowed() {
        return Math.ceil(Math.random()) == 1;
    }

    public double price() {
        return Math.round(Math.random() * 500) + 100;
    }

    public int flightNumber() {
        return (int) Math.ceil(Math.random() * 999);
    }

    public Duration duration(){
        int minutes = (int)Math.ceil(Math.random()*120 + 30);
        Duration flightDuration = Duration.ofMinutes(minutes);
        return flightDuration;
    }

    public String airline() {
        int seed = (int) (Math.floor(Math.random() * 10));
        String airline = "";
        switch (seed) {
            case 0:
                airline = "Qatar Airways";
                break;
            case 1:
                airline = "Singapore Airlines";
                break;
            case 2:
                airline = "Emirates";
                break;
            case 3:
                airline = "Japan Airlines";
                break;
            case 4:
                airline = "Turkish Airlines";
                break;
            case 5:
                airline = "Air France";
                break;
            case 6:
                airline = "Korean Air";
                break;
            case 7:
                airline = "Swiss International Air";
                break;
            case 8:
                airline = "British Airways";
                break;
            case 9:
                airline = "Lufthansa";
                break;

        }
        return airline;
    }


}
