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
        this.setDuration((getFDR().duration())); 

        LocalDateTime temp = inputDateTime;
        this.setDepartureDateTime(temp);
        this.setDepartureDate(temp.toLocalDate());
        this.setDepartureTime(temp.toLocalTime());

        temp = inputDateTime.plus(getDuration());
        this.setArrivalDateTime(temp);
        this.setArrivalDate(temp.toLocalDate());
        this.setArrivalTime(temp.toLocalTime());
    }

}
