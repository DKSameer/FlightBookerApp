package flights.booker;

import java.time.YearMonth;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookingConfirmation {
    
    private String name;
    private String cardNumber;
    private YearMonth expiryDate;
    private String issue;
    private boolean bookingConfirmed;
    private BookingResponse bookingResponse;
    
    private BookingConfirmation(){}

    public BookingConfirmation(String name, String cardNumber, YearMonth expiryDate) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.bookingConfirmed = true;
        confirmBookingDetails();
        if(bookingConfirmed){
            bookingResponse = new BookingResponse(bookingConfirmed, "Payment information verified successfully");
        } else {
            bookingResponse = new BookingResponse(bookingConfirmed, issue);
        }
    }

    
    private void confirmBookingDetails(){
        if(containsIllegalCharacters(name)){
            issue = "Illegal characters in name";
            bookingConfirmed = false;
        } else if (cardNumber.length() != 16){
            issue = "Invalid card number";
            bookingConfirmed = false;
        } else if (expiryDate.isBefore(YearMonth.now())){
            issue = "Card has already expired";
            bookingConfirmed = false;
        }
    }

    private boolean containsIllegalCharacters(String toExamine) {
        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
        Matcher matcher = pattern.matcher(toExamine);
        return matcher.find();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public boolean isBookingConfirmed() {
        return bookingConfirmed;
    }

    public void setBookingConfirmed(boolean bookingConfirmed) {
        this.bookingConfirmed = bookingConfirmed;
    }

    public BookingResponse getBookingResponse() {
        return bookingResponse;
    }

    public void setBookingResponse(BookingResponse bookingResponse) {
        this.bookingResponse = bookingResponse;
    }

	public YearMonth getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(YearMonth expiryDate) {
		this.expiryDate = expiryDate;
	}

}
