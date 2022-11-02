package flights.generator.Flights;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.apache.el.util.Validation;

public class Flight {

    // Input data
    String origin;
    String destination;
    // Simple randomised attributes
    String airline;
    int flightNumber;
    boolean luggageAllowed;
    double price;
    // Departure
    LocalDate departureDate;
    LocalTime departureTime;
    LocalDateTime departureDateTime;
    // Arrival
    LocalTime arrivalTime;
    LocalDate arrivalDate;
    LocalDateTime arrivalDateTime;
    // Randomised flight duration
    Duration duration;
    // Flight Data randomiser
    FlightDataRandomiser FDR = new FlightDataRandomiser();

    public Flight(String inputOrigin, String inputDestination, LocalDate inputDate){
        //Defensive ProgrammingValidation.notNull(inputDate);
        if (inputOrigin == null || inputDestination == null ||inputDate == null) {
            throw new IllegalArgumentException("Flight constructor cannot contain null arguments");
        }
        this.origin = inputOrigin;
        this.destination = inputDestination;
        
        initialiseDateTimes(inputDate);
        this.flightNumber = FDR.flightNumber();
        this.luggageAllowed = FDR.luggageAllowed();
        this.price = FDR.price();
        this.airline = FDR.airline();
    }

    private void initialiseDateTimes(LocalDate inputDate){
        //Flight duration
        this.duration = FDR.duration();
        // Initialize randomised flight departure date and time from input
        this.departureDate = FDR.date(inputDate);
        this.departureTime = FDR.departureTime();
        this.departureDateTime = LocalDateTime.of(departureDate,departureTime);
        // Initialize arrival date and time from departure info and duration
        this.arrivalDateTime = departureDateTime.plus(duration);
        this.arrivalDate = arrivalDateTime.toLocalDate();
        this.arrivalTime = arrivalDateTime.toLocalTime();
    }

    public String getLocation() {
        return origin;
    }

    public void setLocation(String location) {
        this.origin = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public boolean isLuggageAllowed() {
        return luggageAllowed;
    }

    public void setLuggageAllowed(boolean luggageAllowed) {
        this.luggageAllowed = luggageAllowed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public FlightDataRandomiser getFDR() {
        return FDR;
    }

    public void setFDR(FlightDataRandomiser fDR) {
        FDR = fDR;
    }

}