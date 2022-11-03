package flights.booker;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/book")
@CrossOrigin(origins="*")
public class BookerController {
	
	public BookerController(){
	}

	@PostMapping
    public ResponseEntity sendBooking(@RequestBody BookingInput bookingInput) throws URISyntaxException {//
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
		YearMonth parsedDate = YearMonth.parse(bookingInput.getExpiryDate(),formatter);
		BookingConfirmation confirmation = new BookingConfirmation(bookingInput.getName(),bookingInput.getCardNumber(),parsedDate);
        return ResponseEntity.created(new URI("/book")).body(confirmation.getBookingResponse());
    }
}
