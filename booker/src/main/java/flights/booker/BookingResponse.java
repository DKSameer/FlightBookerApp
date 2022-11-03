package flights.booker;

public class BookingResponse {

    boolean bookingConfirmed;
    String message;

    public BookingResponse(boolean bookingConfirmed, String message) {
        this.bookingConfirmed = bookingConfirmed;
        this.message = message;
    }

    public boolean isBookingConfirmed() {
        return bookingConfirmed;
    }

    public void setBookingConfirmed(boolean bookingConfirmed) {
        this.bookingConfirmed = bookingConfirmed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
