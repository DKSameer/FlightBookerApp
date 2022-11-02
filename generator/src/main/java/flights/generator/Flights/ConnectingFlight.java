package flights.generator.Flights;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConnectingFlight extends Flight {

    public ConnectingFlight(String inputOrigin, String inputDestination, LocalDateTime inputDateTime){
        //Defensive ProgrammingValidation.notNull(inputDate);
        super(inputOrigin, inputDestination, inputDateTime.toLocalDate());
        //TODO Auto-generated constructor stub
       
        updateFlightDateTimes(inputDateTime);
    }

    private void updateFlightDateTimes(LocalDateTime inputDateTime){
        //Flight duration
        this.duration = FDR.duration();

        this.departureDateTime = inputDateTime;
        this.departureDate = inputDateTime.toLocalDate();
        this.departureTime = inputDateTime.toLocalTime();

        this.arrivalDateTime = departureDateTime.plus(duration);
        this.arrivalDate = arrivalDateTime.toLocalDate();
        this.arrivalTime = arrivalDateTime.toLocalTime();
    }

}
