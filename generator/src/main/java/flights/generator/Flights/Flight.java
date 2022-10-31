import java.time.LocalDate;
import java.time.LocalTime;
import org.projectlombok.Data;

@Data
public class Flight {

    
    int flightNumber;
    LocalDate date;
    LocalTime time;
    boolean luggageAllowed;
    double price;
    String airline;

    public Flight(LocalDate date){
        this.number = (int)Math.ceil(Math.random()*900);
        randomiseDate(date);
        randomiseTime();
        this.luggageAllowed = Math.ceil(Math.random())==1;
        this.price = Math.round(Math.random()*300);
    }

    private void randomiseDate(LocalDate date){
        if (Math.ceil(Math.random())==1){
            date.minusDays((int)Math.ceil(Math.random()*3.99));
        } else {
            date.plus((int)Math.ceil(Math.random()*3.99));
        }
    }

    private void randomiseTime(){
        int hour = Math.floor(randomSeed*24);
        int minutes = Math.floor(randomSeed*4);
        this.time = LocalTime().of(hour);
        switch(minutes){
            case 0: this.time.plusMinutes(15);
            case 1: this.time.plusMinutes(15);
            case 2: this.time.plusMinutes(15);
            case 3: this.time.plusMinutes(15);
        }
    }
}

