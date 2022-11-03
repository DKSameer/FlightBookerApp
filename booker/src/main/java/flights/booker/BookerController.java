package flights.booker;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity sendBooking(@RequestBody BookingConfirmation confirmBooking) throws URISyntaxException {//
		BookingConfirmation confirmation = new BookingConfirmation(confirmBooking.getName(),confirmBooking.getCardNumber(),confirmBooking.getExpiryDate());
        return ResponseEntity.created(new URI("/book")).body(confirmation.getBookingResponse());
    }
}
