package flights.generator.Flights;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "flight")
public class FlightRequest {
	private LocalDate date;
	private String origin;
	private String destination;
	
	@Id
    @GeneratedValue
    private Long id;
	
	public FlightRequest(Long id,LocalDate date, String origin, String destination) {
		super();
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		
		//call generate flightlist
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Long getId() {
		return id;
	}

}
